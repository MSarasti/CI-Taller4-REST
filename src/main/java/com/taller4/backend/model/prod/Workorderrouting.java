package com.taller4.backend.model.prod;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * The persistent class for the workorderrouting database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Workorderrouting.findAll", query="SELECT w FROM Workorderrouting w")
public class Workorderrouting implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkorderroutingPK id;

	private BigDecimal actualcost;

	private LocalDate actualenddate;

	private BigDecimal actualresourcehrs;

	private LocalDate actualstartdate;

	private LocalDate modifieddate;

	private BigDecimal plannedcost;

	private LocalDate scheduledenddate;

	private LocalDate scheduledstartdate;

	//bi-directional many-to-one association to Location
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="locationid", insertable=false, updatable=false)
	private Location location;

	//bi-directional many-to-one association to Workorder
	@ManyToOne
	@JoinColumn(name="workorderid", insertable=false, updatable=false)
	private Workorder workorder;
	
	@Transient
	private Integer wId;

	public Workorderrouting() {
	}

	public WorkorderroutingPK getId() {
		return this.id;
	}

	public void setId(WorkorderroutingPK id) {
		this.id = id;
	}

	public BigDecimal getActualcost() {
		return this.actualcost;
	}

	public void setActualcost(BigDecimal actualcost) {
		this.actualcost = actualcost;
	}

	public LocalDate getActualenddate() {
		return this.actualenddate;
	}

	public void setActualenddate(LocalDate actualenddate) {
		this.actualenddate = actualenddate;
	}

	public BigDecimal getActualresourcehrs() {
		return this.actualresourcehrs;
	}

	public void setActualresourcehrs(BigDecimal actualresourcehrs) {
		this.actualresourcehrs = actualresourcehrs;
	}

	public LocalDate getActualstartdate() {
		return this.actualstartdate;
	}

	public void setActualstartdate(LocalDate actualstartdate) {
		this.actualstartdate = actualstartdate;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public BigDecimal getPlannedcost() {
		return this.plannedcost;
	}

	public void setPlannedcost(BigDecimal plannedcost) {
		this.plannedcost = plannedcost;
	}

	public LocalDate getScheduledenddate() {
		return this.scheduledenddate;
	}

	public void setScheduledenddate(LocalDate scheduledenddate) {
		this.scheduledenddate = scheduledenddate;
	}

	public LocalDate getScheduledstartdate() {
		return this.scheduledstartdate;
	}

	public void setScheduledstartdate(LocalDate scheduledstartdate) {
		this.scheduledstartdate = scheduledstartdate;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Workorder getWorkorder() {
		return this.workorder;
	}

	public void setWorkorder(Workorder workorder) {
		this.workorder = workorder;
	}

}