package com.infosys.destination.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DomainAuditable implements Serializable{

	private static final long serialVersionUID = -651493104461543900L;
	
	/*
	 * @CreatedBy
	 * 
	 * @Column(name = "created_by", nullable = false, length = 50, updatable =
	 * false)
	 * 
	 * @JsonIgnore private String createdBy;
	 */
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

	/*
	 * @LastModifiedBy
	 * 
	 * @Column(name = "last_modified_by", length = 50)
	 * 
	 * @JsonIgnore private String lastModifiedBy;
	 */

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();
    
	/*
	 * public String getCreatedBy() { return createdBy; }
	 * 
	 * public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
	 */

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

	/*
	 * public String getLastModifiedBy() { return lastModifiedBy; }
	 * 
	 * public void setLastModifiedBy(String lastModifiedBy) { this.lastModifiedBy =
	 * lastModifiedBy; }
	 */

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}