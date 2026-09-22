# 🧪 Mini LIMS - Laboratory Information Management System Backend

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot)
![Database](https://img.shields.io/badge/Database-H2%20In--Memory-blue?style=flat-square&logo=h2)
![Build](https://img.shields.io/badge/Build-Maven-red?style=flat-square&logo=apachemaven)

## 📌 Sobre o Projeto

O **Mini LIMS** é uma API REST backend desenvolvida em **Java** com **Spring Boot** que simula os processos fundamentais de um Sistema de Gestão de Informação de Laboratório (*Laboratory Information Management System*).

A aplicação foi projetada para gerir o ciclo de vida de amostras laboratoriais (recebimento, análise de parâmetros, validação de conformidade e reavaliação de estado) mantendo uma **trilha de auditoria em tempo real** (*Audit Trail*) para conformidade e rastreabilidade dos dados.

---

## 🏛️ Arquitetura do Sistema

O projeto segue a arquitetura padrão em camadas (**Layered Architecture**) para garantir a separação clara de responsabilidades, testabilidade e manutenibilidade do código:

```text
com.labware.minilims
├── 📁 config        # Configurações do Spring e Seeding de Dados de Teste
├── 📁 controllers   # Camada REST (Endpoints HTTP)
├── 📁 domain        # Modelo de Domínio (Entidades JPA e Enums)
│   ├── 📁 entities  # Specimen, ResultAnalysis, ParameterAnalysis, AuditLog
│   └── 📁 enums     # Status (RECEIVED, ANALYSIS, APPROVED, REJECTED)
├── 📁 repositories  # Interfaces Spring Data JPA para Persistência
└── 📁 services      # Camada de Regras de Negócio e Auditoria Automática
