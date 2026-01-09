# Kubernetes Secret for DynamoDB configuration
resource "kubernetes_secret" "dynamodb_config" {
  metadata {
    name      = "${var.app_name}-dynamodb-secret"
    namespace = var.namespace
  }

  data = {
    AWS_REGION                    = data.terraform_remote_state.dynamodb.outputs.aws_region
    DYNAMODB_TABLE_NAME           = data.terraform_remote_state.dynamodb.outputs.dynamodb_table_name
    DYNAMODB_PAYMENTS_TABLE_NAME  = data.terraform_remote_state.dynamodb.outputs.dynamodb_payments_table_name
  }

  type = "Opaque"
}

# Kubernetes Deployment
resource "kubernetes_deployment" "app" {
  metadata {
    name      = "${var.app_name}-deployment"
    namespace = var.namespace
    labels = {
      app = var.app_name
    }
  }

  spec {
    replicas = var.replicas

    selector {
      match_labels = {
        app = var.app_name
      }
    }

    template {
      metadata {
        labels = {
          app = var.app_name
        }
      }

      spec {
        container {
          name  = var.app_name
          image = "${data.terraform_remote_state.infra.outputs.ecr_payments_url}:${var.image_tag}"

          port {
            container_port = var.container_port
          }

          env {
            name = "AWS_REGION"
            value_from {
              secret_key_ref {
                name = kubernetes_secret.dynamodb_config.metadata[0].name
                key  = "AWS_REGION"
              }
            }
          }

          env {
            name = "DYNAMODB_TABLE_NAME"
            value_from {
              secret_key_ref {
                name = kubernetes_secret.dynamodb_config.metadata[0].name
                key  = "DYNAMODB_TABLE_NAME"
              }
            }
          }

          env {
            name = "DYNAMODB_PAYMENTS_TABLE_NAME"
            value_from {
              secret_key_ref {
                name = kubernetes_secret.dynamodb_config.metadata[0].name
                key  = "DYNAMODB_PAYMENTS_TABLE_NAME"
              }
            }
          }

          resources {
            limits = {
              cpu    = "500m"
              memory = "512Mi"
            }
            requests = {
              cpu    = "250m"
              memory = "256Mi"
            }
          }

          liveness_probe {
            http_get {
              path = "/api/health"
              port = var.container_port
            }
            initial_delay_seconds = 60
            period_seconds        = 10
          }

          readiness_probe {
            http_get {
              path = "/api/health"
              port = var.container_port
            }
            initial_delay_seconds = 30
            period_seconds        = 5
          }
        }
      }
    }
  }
}

# Kubernetes Service
resource "kubernetes_service" "app" {
  metadata {
    name      = "${var.app_name}-service"
    namespace = var.namespace
  }

  spec {
    selector = {
      app = var.app_name
    }

    port {
      port        = 80
      target_port = var.container_port
    }

    type = "ClusterIP"
  }
}
