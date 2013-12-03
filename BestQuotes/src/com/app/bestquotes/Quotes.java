package com.app.bestquotes;

public class Quotes {

	int _id;
	int _cat_id;
	int _type;
	String _quote;
	
	
	public int getTYPE() {
		return _type;
	}

	public void setTYPE(int _type) {
		this._type = _type;
	}

	//empty constructor
	public Quotes()
	{}
	
	

	
	
	public Quotes(int _id, int _cat_id, int _type, String _quote) {
		super();
		this._id = _id;
		this._cat_id = _cat_id;
		this._type = _type;
		this._quote = _quote;
	}

	//constructor
	public Quotes(int id,String quote)
	{
		this._id=id;
		//this._cat_id=cat_id;
		this._quote=quote;			
	}
	
  public int getID()
	{
		return this._id;
	}
  
  public void setID(int id)
	{
		 this._id=id;
	}
  
  public int getCAT_ID()
	{
		return this._cat_id;
	}

public void setCAT_ID(int cat_id)
	{
		 this._cat_id=cat_id;
	}
	
public String getQuote()
{
	return this._quote;
}

public void setQuote(String quote)
{
	 this._quote=quote;
}

}
