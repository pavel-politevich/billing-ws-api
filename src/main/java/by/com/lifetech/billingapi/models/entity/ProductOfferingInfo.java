package by.com.lifetech.billingapi.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "om_product_offering_info_v", schema = "tm_om")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"productOffering"})
public class ProductOfferingInfo implements Serializable {
	@Serial
	private static final long serialVersionUID = -4941575997106722135L;

	@Id
	@Column(name = "CODE")
	@JsonIgnore
	private String code;

	@Column(name = "ORDER_CHAIN")
	private String orderChain;
	
	@Column(name = "TH_ORDER")
	private String thOrderCode;
	
	@Column(name = "ORDER_RULE")
	private String orderRule;
	
	@Column(name = "REFUSE_CHAIN")
	private String refuseChain;
	
	@Column(name = "TH_REFUSE")
	private String thRefuseCode;
	
	@Column(name = "REFUSE_RULE")
	private String refuseRule;
	
	@Column(name = "REORDER_CHAIN")
	private String reorderChain;
	
	@Column(name = "TH_REORDER")
	private String thReorderCode;
	
	@Column(name = "REORDER_RULE")
	private String reorderRule;
	
	@Column(name = "NAME_EN")
	private String nameEn;
	
	@Column(name = "NAME_RU")
	private String nameRu;
	
	@Column(name = "BILLING_COMPONENT")
	private String billingComponent;
	
	@Column(name = "BILLING_PRODUCT")
	private String billingProduct;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CODE", referencedColumnName = "CODE")
	@ToString.Exclude
	@JsonIgnore
	private ProductOffering productOffering;
	
}
