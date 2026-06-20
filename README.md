[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/RBBavBFg)
# Atlas Freight

## Overview

Atlas Freight is a Java Web application developed for the Software Development course at the Federal University of Rio Grande do Sul (UFRGS).

The system processes logistics orders and automatically calculates freight costs, estimated delivery dates, and delivery priorities based on configurable business rules.

The application is built using:

* Java 17
* Jakarta Servlet
* JSP
* Apache Tomcat 10
* Maven
* Docker
* Tablesaw
* JUnit 5
* Mockito

---

## Objective

The system receives:

* A freight company configuration file
* A list of pending orders

After processing, the application generates:

* Freight cost calculations
* Estimated delivery dates
* Delivery priorities
* A downloadable CSV report

---

## Main Features

### Freight Processing

Calculates freight costs using configurable factors loaded from external files.

### Delivery Estimation

Calculates delivery dates according to distance, service type, and company configuration.

### Priority Classification

Classifies orders as:

* URGENT
* NORMAL
* LONG_DISTANCE

### CSV Import

Allows users to upload:

* Company configuration files
* Order lists

### CSV Export

Generates a final file:

```text
logistica_finalizada.csv
```

containing all processed freight information.

---

## Architecture

The project follows the MVC (Model-View-Controller) pattern with additional layers for data access, validation, processing, and export.

### Model

Represents the business domain:

* Order
* Freight
* FreightCompany
* Priority
* ServiceType

### View

JSP pages responsible for presenting information to the user.

### Controller

Servlets responsible for:

* Receiving uploaded files
* Processing freight calculations
* Displaying results
* Exporting processed data

### Provider

Responsible for loading data from external sources.

Current implementations:

* CsvCompanyProvider
* CsvOrderProvider

### Service

Contains the business processing logic.

* FreightProcessor

### Exporter

Responsible for generating output files.

* CsvFreightExporter

### Util

Contains validation and support classes used throughout the application.

* ClientValidator
* CompanyValidator
* DateValidator
* DistanceValidator
* OrderIdValidator
* WeightValidator

---

## Running the Application

### Prerequisites

* Docker Desktop

Verify installation:

```bash
docker --version
```

### Build and Start

From the project root directory:

```bash
docker compose up --build
```

### Access

Open your browser and navigate to:

```text
http://localhost:8080
```

---

## Input Files

### Company Configuration

Example:

```csv
parameter,value
distance_factor,0.05
weight_factor,2.10
express_multiplier,1.50
base_delivery_days,2
express_discount_days,1
```

### Orders

Example:

```csv
order_id,client,distance_km,weight_kg,service_type,order_date
ORD-001,Store A,450,2.5,NORMAL,2026-06-01
ORD-002,Store B,120,0.8,EXPRESSO,2026-06-01
```

---

## Documentation

Additional documentation is available in the GitHub Wiki:

* Project Overview
* Architecture
* UML Diagram
* Business Rules

---

## Authors

* João Victor Prado Trindade (588129)
* Jorge Antônio Noll (343372)
* Arthur Farias Zapata (577298)
* Pedro Henrique Antunes Claudino (579557)

Federal University of Rio Grande do Sul (UFRGS)
