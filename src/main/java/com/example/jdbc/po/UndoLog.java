package com.example.jdbc.po; 

import java.sql.Timestamp;
import java.sql.Timestamp;

public class UndoLog { 

	private String ext; 

	private String xid; 

	private Integer logStatus; 

	private Long branchId; 

	private Timestamp logCreated; 

	private String context; 

	private Timestamp logModified; 

	private Long id; 

	private String rollbackInfo; 

	public void setExt (String ext){ 
	    this.ext=ext; 
	 } 

	public String getExt(){
	    return ext; 
	 } 

	public void setXid (String xid){ 
	    this.xid=xid; 
	 } 

	public String getXid(){
	    return xid; 
	 } 

	public void setLogStatus (Integer logStatus){ 
	    this.logStatus=logStatus; 
	 } 

	public Integer getLogStatus(){
	    return logStatus; 
	 } 

	public void setBranchId (Long branchId){ 
	    this.branchId=branchId; 
	 } 

	public Long getBranchId(){
	    return branchId; 
	 } 

	public void setLogCreated (Timestamp logCreated){ 
	    this.logCreated=logCreated; 
	 } 

	public Timestamp getLogCreated(){
	    return logCreated; 
	 } 

	public void setContext (String context){ 
	    this.context=context; 
	 } 

	public String getContext(){
	    return context; 
	 } 

	public void setLogModified (Timestamp logModified){ 
	    this.logModified=logModified; 
	 } 

	public Timestamp getLogModified(){
	    return logModified; 
	 } 

	public void setId (Long id){ 
	    this.id=id; 
	 } 

	public Long getId(){
	    return id; 
	 } 

	public void setRollbackInfo (String rollbackInfo){ 
	    this.rollbackInfo=rollbackInfo; 
	 } 

	public String getRollbackInfo(){
	    return rollbackInfo; 
	 } 

}