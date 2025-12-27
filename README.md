# 💰 Gestor de Finanzas Personales (CLI)

> Una aplicación de consola robusta y eficiente para la gestión de economía personal, desarrollada aplicando principios sólidos de Programación Orientada a Objetos (POO).

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-En_Desarrollo-green?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

## 📋 Descripción

Este proyecto es una solución de software diseñada para rastrear el flujo de caja personal. A diferencia de scripts simples, este gestor implementa una arquitectura escalable basada en clases, permitiendo diferenciar y procesar distintos tipos de transacciones financieras mediante **polimorfismo**.

El objetivo principal de este desarrollo ha sido consolidar conocimientos avanzados de ingeniería de software en Java, centrándose en la **limpieza del código** y la **extensibilidad**.

## ✨ Características Principales

* **Registro de Transacciones:** Entrada detallada de Ingresos y Gastos con descripción y monto.
* **Cálculo de Balance Dinámico:** Algoritmo que recorre el historial para ofrecer el saldo en tiempo real.
* **Historial Detallado:** Visualización polimórfica de las transacciones (distinción visual entre entradas y salidas).
* **Interfaz de Línea de Comandos (CLI):** Diseño minimalista y rápido, ideal para entornos Unix/Linux.

## 🛠️ Stack Tecnológico & Conceptos Aplicados

* **Lenguaje:** Java (JDK 17+)
* **Paradigma:** Programación Orientada a Objetos (POO).
* **Conceptos Clave:**
    * **Herencia:** Uso de superclases para lógica compartida.
    * **Polimorfismo:** Tratamiento uniforme de objetos `Gasto` e `Ingreso`.
    * **Clases Abstractas:** Definición de plantillas base para las transacciones.
    * **Collections Framework:** Uso eficiente de `ArrayList` para manejo dinámico de memoria.

## 🏗️ Estructura del Sistema
|-- src
    |-- actions
        |-- Transaction.java
        |-- Ingreso.java
        |-- Gasto.java
    |-- Main.java
    |-- Wallet.java
|-- README.md

## 🏗️ Arquitectura del Sistema

El sistema sigue un diseño modular:

```mermaid
classDiagram
    Transaccion <|-- Ingreso
    Transaccion <|-- Gasto
    Billetera o-- Transaccion
    
    class Transaccion {
        <<Abstract>>
        +monto
        +descripcion
        +mostrarInfo()
    }