# Fresh2Home

## Digital Ecosystem for Sustainable Organic Farming

## Project Overview

Fresh2Home is a Java Swing-based platform designed to support and enhance the organic farming ecosystem. By connecting different enterprises, organizations, and entities like farmers, consumers, quality inspectors, and educational bodies, Fresh2Home aims to streamline distribution, improve product quality, and promote sustainable farming practices. This platform addresses critical barriers faced by local farmers, such as limited market access and distribution inefficiencies, and fosters a transparent, sustainable market for organic products.

# Key Objectives:

- Bridging the gap between farmers and consumers.

- Promoting certified organic products.

- Supporting local, small, and medium-scale farmers with fair pricing.

- Enabling quality assurance and sustainable farming education.

## Key Features

# User Management

- Registration and Authentication: Secure login for all users.

- Role Assignment: Users register as Farmers, Customers, Quality Inspectors, Researchers, Volunteers, or Delivery Personnel, each with role-specific functionalities.

# Product Management

- Product Listing: Farmers list products; Quality Assurance certifies them.

- Purchase and Cart: Customers browse products, add to cart, and complete purchases.

- Order Fulfillment: Delivery Personnel receive order details for product delivery.

# Community and Research

- Research Posting: Researchers share findings on organic farming, accessible to all users.

- Volunteer Support: Farmers can request volunteer assistance for farming tasks.

## User Roles

- Farmer: Lists products, negotiates prices, and fulfills orders.

- Customer: Purchases organic products directly from farmers.

- Quality Assurance: Inspects and certifies products, setting fair prices.

- Researcher: Shares research on sustainable practices.

- Volunteer: Assists farmers with hands-on support.

- Delivery Personnel: Ensures timely and safe delivery of products to customers.

## Use Cases

# Product Listing and Certification:

    Farmers list products, which undergo certification and pricing by Quality Assurance.

# Product Purchase:

    Customers browse, add items to their cart, and purchase products.

# Product Delivery:

    Delivery Personnel handle the logistics of delivering orders to customers.

# Volunteer Assistance:

    Farmers request and select volunteers for specific tasks.

# Research Posting:

    Researchers post findings and engage with the community.

## Architecture and Design

# Ecosystem Model

The project separates UI and backend into distinct folders, following a hierarchical model inspired by the ecosystem:

- Production Enterprise: Supports farmers in listing products and accessing resources for sustainable farming.

- Quality Assurance Enterprise: Verifies and certifies product quality and safety before listing.

- Knowledge Transfer Enterprise: Provides educational resources and facilitates the dissemination of best practices.

- Sales and Delivery Enterprise: Manages the online store, processes orders, and oversees logistics.

## External APIs

Charting Library: Jfreecharts for visual analytics.
Mailing library: Javaxmail for the mail services.

## File Structure

Fresh2Home
├── README.md
├── UML DIAGRAM-Fresh2Home.docx
├── Fresh2Home- OrganicFarming.pdf
├── F2H
├── src
│ ├── pdf
| ├── ui
│ ├── business
│ └── images
├── build.xml
├── dist
└── manifest.mf

## Instructions to Use the Repository

Clone the repository:

git clone https://github.com/Harshitha-Sappidi/Fresh2Home

Navigate to the project directory:

cd Fresh2Home and run the project

## Future Enhancements

- Integration with online payment gateways.

- Real-time tracking of product shipments.

- Mobile application for better accessibility.

## Contributers

    Name   : Harshitha Sappidi
    NUID   : 002416707
    Email  : sappidi.h@northeastern.edu
