package com.app.bestquotes;

public class Categories {

	int _id;
	//int _cat_id;
	String _category;
	
	//empty constructor
	public Categories()
	{}
	
	//constructor
	public Categories(int id,String category)
	{
		this._id=id;
		//this._cat_id=cat_id;
		this._category=category;
			
	}
	public Categories(String category)
	{
		//this._id=id;
		//this._cat_id=cat_id;
		this._category=category;
			
	}
		
  public int getID()
	{
		return this._id;
	}
  
  public void setID(int id)
	{
		 this._id=id;
	}
  

public String getCategory()
{
	return this._category;
}

public void setCategory(String category)
{
	 this._category=category;
}

}
