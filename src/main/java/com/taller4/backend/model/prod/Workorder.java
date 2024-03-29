package com.taller4.backend.model.prod;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the workorder database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Workorder.findAll", query="SELECT w FROM Workorder w")
public class Workorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WORKORDER_WORKORDERID_GENERATOR",allocationSize = 1, sequenceName="WORKORDER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WORKORDER_WORKORDERID_GENERATOR")
	private Integer workorderid;

	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate duedate;

	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate enddate;

	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;

	private Integer orderqty;

	private Integer scrappedqty;

	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startdate;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;

	//bi-directional many-to-one association to Scrapreason
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="scrapreasonid")
	private Scrapreason scrapreason;

	//bi-directional many-to-one association to Workorderrouting
	@JsonIgnore
	@OneToMany(mappedBy="workorder")
	private List<Workorderrouting> workorderroutings;
	
	@Transient
	public Integer pId;

	public Workorder() {
	}

	public Integer getWorkorderid() {
		return this.workorderid;
	}

	public void setWorkorderid(Integer workorderid) {
		this.workorderid = workorderid;
	}

	public LocalDate getDuedate() {
		return this.duedate;
	}

	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}

	public LocalDate getEnddate() {
		return this.enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Integer getOrderqty() {
		return this.orderqty;
	}

	public void setOrderqty(Integer orderqty) {
		this.orderqty = orderqty;
	}

	public Integer getScrappedqty() {
		return this.scrappedqty;
	}

	public void setScrappedqty(Integer scrappedqty) {
		this.scrappedqty = scrappedqty;
	}

	public LocalDate getStartdate() {
		return this.startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Scrapreason getScrapreason() {
		return this.scrapreason;
	}

	public void setScrapreason(Scrapreason scrapreason) {
		this.scrapreason = scrapreason;
	}

	public List<Workorderrouting> getWorkorderroutings() {
		return this.workorderroutings;
	}

	public void setWorkorderroutings(List<Workorderrouting> workorderroutings) {
		this.workorderroutings = workorderroutings;
	}

	public Workorderrouting addWorkorderrouting(Workorderrouting workorderrouting) {
		if(this.workorderroutings==null)
			this.workorderroutings = new ArrayList<>();
		getWorkorderroutings().add(workorderrouting);
		workorderrouting.setWorkorder(this);

		return workorderrouting;
	}

	public Workorderrouting removeWorkorderrouting(Workorderrouting workorderrouting) {
		getWorkorderroutings().remove(workorderrouting);
		workorderrouting.setWorkorder(null);

		return workorderrouting;
	}

}