package com.example.jdbc.po; 

import java.math.BigDecimal;

public class TOrder { 

	private BigDecimal money; 

	private Long userId; 

	private Long productId; 

	private Integer count; 

	private Long id; 

	private Integer status; 

	public void setMoney (BigDecimal money){ 
	    this.money=money; 
	 } 

	public BigDecimal getMoney(){
	    return money; 
	 } 

	public void setUserId (Long userId){ 
	    this.userId=userId; 
	 } 

	public Long getUserId(){
	    return userId; 
	 } 

	public void setProductId (Long productId){ 
	    this.productId=productId; 
	 } 

	public Long getProductId(){
	    return productId; 
	 } 

	public void setCount (Integer count){ 
	    this.count=count; 
	 } 

	public Integer getCount(){
	    return count; 
	 } 

	public void setId (Long id){ 
	    this.id=id; 
	 } 

	public Long getId(){
	    return id; 
	 } 

	public void setStatus (Integer status){ 
	    this.status=status; 
	 } 

	public Integer getStatus(){
	    return status; 
	 } 

}