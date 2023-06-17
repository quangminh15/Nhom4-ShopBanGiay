package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportYT {
	@Id
	Serializable group;
	Long count;
	
}
