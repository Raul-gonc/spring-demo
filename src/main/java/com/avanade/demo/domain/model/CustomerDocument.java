package com.avanade.demo.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_documents")
public class CustomerDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String document;

    public CustomerDocument() {
    }

    public CustomerDocument(Customer customer, String documentValue, DocumentType documentType) {
        this.document = documentValue;
        this.customer = customer;
        this.documentType = documentType;
    }

    // Getters and Setters
    public String getDocumentTypeName() {
        return documentType.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }
}