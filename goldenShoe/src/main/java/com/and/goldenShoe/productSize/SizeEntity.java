package com.and.goldenShoe.productSize;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlTransient;

import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentEntity;

@Entity
@Table(name="size")
public class SizeEntity {
	
	private int sizeID;
	
	@FormParam("size")
	private double size;
	
	Set<ProductSizeAssignmentEntity> assignedSize = new HashSet<ProductSizeAssignmentEntity>();
	
	

	@OneToMany(fetch = FetchType.EAGER, mappedBy="linkedSize", cascade=CascadeType.ALL)
	@XmlTransient
	public Set<ProductSizeAssignmentEntity> getAssignedSize() {
		return assignedSize;
	}

	public void setAssignedSize(Set<ProductSizeAssignmentEntity> assignedSize) {
		this.assignedSize = assignedSize;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getSizeID() {
		return sizeID;
	}

	public void setSizeID(int sizeID) {
		this.sizeID = sizeID;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
	
	

}
