This is a Spring Boot-based web application designed to generate QR codes via a REST API.

Features:

Health Check Endpoint:Simple endpoint to verify that the service is running.

QR Code Generation Endpoint:Generates a QR code based on the provided parameters.

Parameters:

  contents (required): The data to encode in the QR code.
  correction (optional): Error correction level (default: L). Supported values: L, M, Q, H.
  size (optional): Size of the QR code in pixels (default: 250).
  type (optional): Image format (default: png). Supported formats: png, jpg, gif, etc.
