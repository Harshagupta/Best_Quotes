package com.app.bestquotes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	private String TAG= DatabaseHandler.class.getCanonicalName();
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "qros";
	// Quotes table name
	private static final String TABLE_QUOTES = "quotes";
	// Category table name
	private static final String TABLE_CATEGORIES = "categories";
	// Quotes Table Columns names
	private static final String Q_KEY_ID = "id";
	private static final String Q_KEY_CAT_ID = "cat_id";
	private static final String Q_KEY_TYPE = "type";
	private static final String Q_KEY_QUOTE = "quote";
	
	// Quotes Table Columns names
	private static final String C_KEY_ID = "id";
	//private static final String C_KEY_CAT_ID = "cat_id";	
	private static final String C_KEY_CATEGORY = "category";
	private SQLiteDatabase db; 
	
	private Context ctx;
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		db=this.getWritableDatabase();
		ctx=context;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
		+ C_KEY_ID + " INTEGER PRIMARY KEY," + C_KEY_CATEGORY + " TEXT" + ")";
		db.execSQL(CREATE_CATEGORY_TABLE);
		Log.d("DB Oncreate", CREATE_CATEGORY_TABLE);
      
 
        

		String CREATE_QUOTES_TABLE = "CREATE TABLE " + TABLE_QUOTES + "("
				+ Q_KEY_ID + " INTEGER PRIMARY KEY," + Q_KEY_CAT_ID + " INTEGER," + Q_KEY_TYPE + " INTEGER,"
				+ Q_KEY_QUOTE + " TEXT" + ")";
		
		db.execSQL(CREATE_QUOTES_TABLE);
		Log.d("DB Oncreate", CREATE_QUOTES_TABLE);
	
		
		
		ContentValues values = new ContentValues();
		
	
		String[] cats={"Amazing","Anniversary","Attitude","Beauty","Birthday","Brainy","Communication","Education","Friendship","God","Happiness","Inspirational","Hardwork","Poetry","Science"};
		
		try{
		for(int i=0;i<cats.length;i++)
		{
			Categories category=new Categories(i,cats[i]);
			
			values.put(C_KEY_ID,category.getID());
			values.put(C_KEY_CATEGORY, category.getCategory()); // Quote	
			db.insert(TABLE_CATEGORIES, null, values);
			
		}
		
		}catch(Exception e)
		{
			Log.d("Category Installation ",e.getMessage());
		}
		Log.d("Category Installation Completed","Quotes Installation Started");
		//Text Quotes Installation
		ContentValues qvalues = new ContentValues();
		String [][] quotes={
				{"0","If you set goals and go after them with all the determination you can muster, your gifts will take you places that will amaze you-Les Brown"},
				{"0","The thing that is really hard, and really amazing, is giving up on being perfect and beginning the work of becoming yourself-Anna Quindlen"},
				{"0",""},
				{"0",""},
				{"0",""},
				{"0",""},
				{"0",""},
				{"0",""},
				{"0",""},
				{"0",""},		
				{"1",""},
				{"0",""},
			    {"0","Vision is the art of seeing what is invisible to others.- Jonathan Swift"},
				{"0","“Love art in yourself, and not yourself in art.” - Constantin Stanislavski"},
				{"0","“Great art picks up where nature ends.”  - Marc Chagall "},
				{"0","“To be an artist is to believe in life.” - Henry Moore"},
				{"0","“Art is not what you see, but what you make others see.” - Edgar Degas"},
				{"0","“A Good artist has less time than ideas.”-  Martin Kippenberger"},
				{"0","“Painting is a means of self-enlightenment.” - John Olsen "},
				{"0","“Art is a lie that makes us realize truth.”-  Pablo Picasso"},
				{"0","“Every artist dips his brush in his own soul, and paints his own nature into his pictures.” - Henry Ward Beecher"},
				{"0","“If a man devotes himself to art, much evil is avoided that happens otherwise if one is idle.”  - Albrecht Durer"},
				{"0","“The artist's world is limitless.It can be found anywhere, far from where he lives or a few feet away.It is always on his doorstep.” - Paul Strand "},
				{"0","“Creativity is allowing yourself to make mistakes. Art is knowing which ones to keep.”- Scott Adams"},
				{"1","“Our beliefs about what we are and what we can be precisely determine what we can be” - Anthony Robbins"},
				{"1","“Attitude is a little thing that makes a big difference.” - Winston Churchill"},
				{"1","“Our life is what our thoughts make it.” - Marcus Aurelius"},
				{"1","Weakness of attitude becomes weakness of character.- Albert Einstein"},
				{"1","Your attitude, not your aptitude, will determine your altitude.- Zig Ziglar "},
				{"1","People may hear your words, but they feel your attitude.- John C. Maxwell"},
				{"1","Our attitude towards others determines their attitude towards us.- Earl Nightingale "},
				{"1","When you pray for anyone you tend to modify your personal attitude toward him.- Norman Vincent Peale  "},
				{"1","It is our attitude at the beginning of a difficult task which, more than anything else, will affect its successful outcome.- William James "},
				{"1","“The worst disability in life is a bad attitude.” - SupaNova Slom"},
				{"1","“An attitude of positive expectation is the mark of the superior personality.” - Brian Tracy"},
				{"1","“Stop searching the world for treasure, the real treasure is in yourself.” - Pablo"},
				{"1","If you don't think every day is a good day, just try missing one.” - Cavett Robert"},
				{"1","Having a positive mental attitude is asking how something can be done rather than saying it can't be done.- Bo Bennett  "},
				{"1","“No guts, no story.” - Chris Brady"},
				{"1","“All things are ready, if our mind be so.” - William Shakespeare"},
				{"1","For success, attitude is equally as important as ability.- Walter Scott "},
				{"1","“Every day, you get the opportunity to change your life. Change what you do not want. Change what makes you unhappy.”- Rodolfo Costa"},
				{"1","“Poverty is not a circumstance, it's an attitude.” - Rita Gonzalez"},
				{"1","The greatest discovery of all time is that a person can change his future by merely changing his attitude.- Oprah Winfrey "},
				{"1","Excellence is not a skill. It is an attitude.- Ralph Marston"},
				{"1","“A positive attitude from you tends to produce a positive attitude toward you.” - Deborah Day"},
				{"1","“Never let your failures go to your heart or your successes go to your head.” - Soichiro Honda "},
				{"1",".“Keep your face always toward the sunshine - and shadows will fall behind you.- Walt Whitman"},
				{"1","I do not believe in taking the right decision, I take a decision and make it right.- Muhammad Ali Jinnah"},
				{"2","Our birthdays are feathers in the broad wing of time.  – By Jean Paul Richter"},
				{"2","It takes a long time to grow young. – By Pablo Picasso"},
				{"2","The first hundred years are the hardest. – Wilson Mizner"},
				{"2","At twenty years of age, the will reigns; at thirty, the wit; and at forty, the judgement.– By Benjamin Franklin"},
				{"2","A diplomat is a man who always remembers a woman's birthday but never remembers her age. – By Robert Frost"},
				{"2","All the world is birthday cake, so take a piece, but not too much. – By George Harrison"},
				{"2","Growing old is mandatory; growing up is optional. - By Chili Davis"},
				{"2","We are always the same age inside.– By Gertrude Stein"},
				{"2","You are only young once, but you can be immature for a lifetime. – By John P. Grier"},
				{"2","You were born an original. Don't die a copy. – By John Mason"},
				{"2","Youth is the gift of nature, but age is the work of art. – By Garson"},
				{"2","Age is an issue of mind over matter. If you don't mind, it doesn't matter. – By Mark Twain"},
				{"2","Each ten years of a man's life has its own fortunes, its own hopes, its own desires. – By Goethe"},
				{"2","The first sign of maturity is the discovery that the volume knob also turns to the left.  – By Jerry M. Wright"},
				{"2","Inside every older person is a younger person wondering what happened.  – By Jennifer Yane"},
				{"2","Just remember, once you're over the hill you begin to pick up speed.  – By Charles Schulz"},
				{"2","Large streams from little fountains flow, Tall oaks from little acorns grow. – By David Everett"},
				{"2","Middle age is having a choice between two temptations and choosing the one that'll get you home earlier. – By Dan Bennett"},
				{"2","The old believe everything; the middle- By aged suspect everything; the young know everything.– By Oscar Wilde"},
				{"2","Time flies like an arrow. Fruit flies like a banana. – By Groucho Marx"},
				{"2","Very early, I knew that the only object in life was to grow.– By Margaret Fuller"},
				{"2"," Youth is a disease from which we all recover.  – By Dorothy Fulheim"},
				{"2","Birthdays are good for you. Statistics show that the people who have the most live the longest.  – By Larry Lorenzoni"},
				{"2","Keep true to the dream of thy youth. – By Friedrich Von Schiller"},
				{"3","“Price is what you pay. Value is what you get.” - Warren Buffett"},
				{"3","“Practice does not make perfect. Perfect practice makes perfect.” - Vince Lombardi Jr."},
				{"3","Sometimes when you innovate, you make mistakes.It is best to admit them quickly, and get on with improving your other innovations.- Steve Jobs"},
				{"3","All lasting business is built on friendship.- Alfred A. Montapert"},
				{"3","No enterprise is more likely to succeed than one concealed from the enemy until it is ripe for execution.- Niccolo Machiavelli "},
				{"3","The secret of business is to know something that nobody else knows.- Aristotle Onassis"},
				{"3","Every young man would do well to remember that all successful business stands on the foundation of morality.- Henry Ward Beecher"},
				{"3","In the business world, everyone is paid in two coins: cash and experience. Take the experience first; the cash will come later.- Harold S. Geneen"},
				{"3","“Whenever you see a successful business, someone once made a courageous decision.” - Peter F. Drucker"},
				{"3","“In business, three things are necessary: knowledge, temper, and time.” - Owen Felltham"},
				{"3","“A business has to be involving, it has to be fun, and it has to exercise your creative instincts.” - Richard Branson"},
				{"3"," “Early to bed, early to rise, work like hell, and advertise” - Ted Turner"},
				{"3","“An entrepreneur without funding is a musician without an instrument.” - Robert A. Rice Jr."},
				{"3","“A small profit it better than a big loss” - Ron Rash, Serena"},
				{"3","Talent can be hired, money has to be earned.” - Vishnu"},
				{"3","The entrepreneur always searches for change, responds to it, and exploits it as an opportunity.- Peter Drucker"},
				{"3","Business is a combination of war and sport.- Andre Maurois"},
				{"3","It takes more than capital to swing business.You've got to have the A.I.D. degree to get by- Advertising,Initiative and Dynamics.- Isaac Asimov"},
				{"3","“Business is all about solving people's problems - at a profit.” - Paul Marsden"},
				{"3","“Make sure your business is creating a service experience so good that it demands loyalty.” -Steve Maraboli"},
				{"3","“Listen to your customers, not your competitors.” - Joel Spolsky"},
				{"3","“Your customers are responsible for your company’s reason for existing.” - Marilyn Suttle"},
				{"3","“The best way to kill competition is to partner it” - Siddharth Joshi"},
				{"3","The greatest ability in business is to get along with others and to influence their actions.- John Hancock"},
				{"3","“Marketing is not the art of finding clever ways to dispose of what you make. It is the art of creating genuine customer value.” - Philip Kotler"},
				{"4","“A friend is someone who knows all about you and still loves you.” - Elbert Hubbard"},
				{"4","“Good friends, good books, and a sleepy conscience: this is the ideal life.”- Mark Twain"},
				{"4","“A friend is one that knows you as you are, understands where you have been, accepts what you have become, and still, gently allows you to grow.” - William Shakespeare"},
				{"4","“Life is an awful, ugly place to not have a best friend.” - Sarah Dessen"},
				{"4","“Words are easy, like the wind; Faithful friends are hard to find.” - William Shakespeare"},
				{"4","“The best mirror is an old friend.” - George Herbert"},
				{"4","“Anybody can sympathise with the sufferings of a friend, but it requires a very fine nature to sympathise with a friend'ssuccess.” - Oscar Wilde"},
				{"4","“Wishing to be friends is quick work, but friendship is a slow ripening fruit.” - Aristotle"},
				{"4","“If you have two friends in your lifetime, you're lucky. If you have one good friend, you're more than lucky.” - S.E. Hinton"},
				{"4","“No friendship is an accident. ” - O. Henry"},
				{"4","“Friendship is delicate as a glass, once broken it can be fixed but there will always be cracks” - Waqar Ahmed"},
				{"4","“Be slow to fall into friendship, but when you are in, continue firm and constant.” - Socrates"},
				{"4","“A friend is someone who gives you total freedom to be yourself.” - Jim Morrison"},
				{"4","“Friendship is always a sweet responsibility, never an opportunity.” - Kahlil Gibran"},
				{"4","“Friendship improves happiness, and abates misery, by doubling our joys, and dividing our grief” - Marcus Tullius Cicero"},
				{"4","“The language of friendship is not words but meanings.” - Henry David Thoreau"},
				{"4","“Friends are the family you choose.” - Jess C. Scott"},
				{"4","“Friends should be like books, few, but hand-selected. ” - C.J. Langenhoven"},
				{"4","“A friend is one who walks in when others walk out.” - Walter Winchell"},
				{"4","“A wise man gets more use from his enemies than a fool from his friends.” - Baltasar Gracián"},
				{"4","“Be slow in choosing a friend, slower in changing.” - Benjamin Franklin"},
				{"4","“The better part of one's life consists of his friendships.” - Abraham Lincoln"},
				{"4","“The most I can do for my friend is simply to be his friend.” - Henry David Thoreau"},
				{"4","“To throw away an honest friend is, as it were, to throw your life away” - Sophocles"},
				{"4","“Home is anywhere that you know all your friends and all your enemies.”- Orson Scott Card"},
				{"5","“Don't cry because it's over, smile because it happened.” - Dr. Seuss"},
				{"5","“People are just as happy as they make up their minds to be.” - Abraham Lincoln"},
				{"5","“Happiness is not something ready made. It comes from your own actions.” - Dalai Lama XIV"},
				{"5","“Count your age by friends, not years. Count your life by smiles, not tears.” - John Lennon"},
				{"5","“Success is getting what you want, happiness is wanting what you get” - W.P. Kinsella"},
				{"5","The purpose of our lives is to be happy.- Dalai Lama"},
				{"5","True happiness comes from the joy of deeds well done, the zest of creating things new.- Antoine de Saint-Exupery"},
				{"5","There is only one way to happiness and that is to cease worrying about things which are beyond the power of our will.- Epictetus"},
				{"5","Happiness doesn't depend on any external conditions, it is governed by our mental attitude.- Dale Carnegie "},
				{"5","The first recipe for happiness is: avoid too lengthy meditation on the past.- Andre Maurois"},
				{"5","“No medicine cures what happiness cannot.” - Gabriel Garcí­a Márquez"},
				{"5","“HAPPINESS is ONLY REAL WHEN SHARED” - Jon Krakauer"},
				{"5","“The best way to cheer yourself is to try to cheer someone else up.” - Mark Twain"},
				{"5",".“Happiness makes up in height for what it lacks in length.” - Robert Frost"},
				{"5","The two enemies of human happiness are pain and boredom.- Arthur Schopenhauer"},
				{"5","“The happiness of your life depends upon the quality of your thoughts.” - Marcus Aurelius"},
				{"5","“The more you praise and celebrate your life, the more there is in life to celebrate.” - Oprah Winfrey"},
				{"5","“Happiness consists in frequent repetition of pleasure” - Arthur Schopenhauer"},
				{"5","Happiness is found in doing, not merely possessing.- Napoleon Hill "},
				{"5","Do not speak of your happiness to one less fortunate than yourself.- Plutarch"},
				{"5","“Be believing, be happy, don't get discouraged. Things will work out.” - Gordon B. Hinckley"},
				{"5","Happiness does not lie in happiness, but in the achievement of it.- Fyodor Dostoevsky "},
				{"5","Happiness comes only when we push our brains and hearts to the farthest reaches of which we are capable.- Leo Rosten"},
				{"5","“Action may not always bring happiness, but there is no happiness without action. ” - William James"},
				{"5","Happiness is like a cloud, if you stare at it long enough, it evaporates.- Sarah McLachlan "},
				{"6",".“It's hard to beat a person who never gives up.” - Babe Ruth"},
				{"6","“If you try and lose then it isn't your fault. But if you don't try and we lose, then it's all your fault.” - Orson Scott Card"},
				{"6","“The three great essentials to achieve anything worthwhile are, first, hard work; second, stick-to-itiveness; third, common sense.” - Thomas A. Edison"},
				{"6","“I'm a greater believer in luck, and I find the harder I work the more I have of it” - Thomas Jefferson"},
				{"6","“The difference between ordinary and extraordinary is that little extra.” - Jimmy Johnson"},
				{"6","“The dictionary is the only place that success comes before work & hard work can help you accomplish anything.” - Vince Lombardi Jr."},
				{"6","“I do not care about happiness simply because I believe that joy is something worth fighting for.” - Criss Jami"},
				{"6","“All good work requires self-revelation.” - Sidney Lumet"},
				{"6","“Hard work without talent is a shame, but talent without hard work is a tragedy” - Robert Half"},
				{"6","“If you are not working towards something, your life will end with nothing.” - Habeeb Akande"},
				{"6","Talent in cheaper than table salt. What separates the talented individual from the successful one is a lot of hard work.- Stephen King"},
				{"6","I learned the value of hard work by working hard.- Margaret Mead"},
				{"6","I do not believe in excuses. I believe in hard work as the prime solvent of life's problems.- James Cash Penny"},
				{"6","For every two minutes of glamour, there are eight hours of hard work.- Jessica Savitch"},
				{"6","Reality is easy. It's deception that's the hard work.- Lauryn Hill"},
				{"6","Life grants nothing to us mortals without hard work.- Horace "},
				{"6","Plans are only good intentions unless they immediately degenerate into hard work.- Peter Drucker "},
				{"6","A good idea is about ten percent and implementation and hard work, and luck is 90 percent.- Guy Kawasaki"},
				{"6","Through hard work, perseverance and a faith in God, you can live your dreams.- Benjamin Carson"},
				{"6","The only thing that overcomes hard luck is hard work.- Harry Golden"},
				{"6","Goodness and hard work are rewarded with respect.- Luther Campbell"},
				{"6","Luck is great, but most of life is hard work.- Iain Duncan Smith"},
				{"6","If the power to do hard work is not a skill, it's the best possible substitute for it.- James A. Garfield  "},
				{"6","Hard work keeps the wrinkles out of the mind and spirit.- Helena Rubinstein "},
				{"6","The trouble with opportunity is that it always comes disguised as hard work.- Herbert Prochnow"},
				{"6","Without hard work and discipline it is difficult to be a top professional.- Jahangir Khan "},
				{"6","Happiness is the real sense of fulfillment that comes from hard work.- Joseph Barbara"},
				{"6","Hard work always pays off, whatever you do.- Dustin Lynch "},
				{"6","A lot of hard work has to go into your career, and preparation, and being your best at all times.- Leven Rambin"},
				{"6","Life is either a daring adventure or nothing.- helen keller"},
				{"6","Experience is a hard teacher because it gives the test first,the lesson afterwards.- Vernon Sanders Law"},
				{"6","“Hard work doesn't guarantee success, but improves its chances.”- B.J Gupta"},
				{"6","If it wasn't hard, everyone would do it. It's the hard that makes it great.- Tom Hanks"},
				{"6","“Striving for success without hard work is like trying to harvest where you haven't planted.”- David Bly "},
				{"6","Success is simple. Do what's right, the right way, at the right time.- Arnold H "},
				{"6","Success is focusing the full power of all you are on what you have a burning desire to achieve.- Wilfred Peterson"},
				{"6","Some people dream of success while others wake up and work hard at it.- Winston Churchill "},
				{"6","The distance between dreams and achievements can only be traveled in the vehicle of hard work and determination.- Senora Roy"},
				{"6","Your hard work might not pay off today or tomorrow, but it will eventually pay off.- Sonya Parker"},
				{"6","Work hard in silence, let your success be your noise.- Ritu Ghatourey"},
				{"7","Death, like birth, is a secret of Nature.- Marcus Aurelius"},
				{"7","“We risk losing what nature is if we couch its value in human terms.” - Richard Black"},
				{"7","“The first and fundamental law of Nature, which is, to seek peace and follow it.” - Thomas Hobbes"},
				{"7","The art of medicine consists in amusing the patient while nature cures the disease.- Voltaire"},
				{"7","Good nature is worth more than knowledge, more than money, more than honor, to the persons who possess it.- Henry Ward Beecher"},
				{"7","“Nature cannot be commanded except by being obeyed.” - Francis Bacon"},
				{"7","Nature does not hurry, yet everything is accomplished.- Lao Tzu"},
				{"7","“Nature is a mutable cloud which is always and never the same” - Ralph Waldo Emerson"},
				{"7","“Nature provides exceptions to every rule.” - Margaret Fuller"},
				{"7","Nature uses human imagination to lift her work of creation to even higher levels.- Luigi Pirandello"},
				{"7","Understanding the laws of nature does not mean that we are immune to their operations.- David Gerrold"},
				{"7","“The happiest man is he who learns from nature the lesson of worship” - Ralph Waldo Emerson"},
				{"7","We do not see nature with our eyes, but with our understandings and our hearts.- William Hazlitt"},
				{"7","There are no passengers on spaceship earth. We are all crew.- Marshall McLuhan"},
				{"7","“Nature is pleased with simplicity. And nature is no dummy” - Isaac Newton"},
				{"7","Some people walk in the rain, others just get wet.- Roger Miller"},
				{"7","I believe a leaf of grass is no less than the journey-work of the stars.- Walt Whitman"},
				{"7","The butterfly counts not months but moments, and has time enough.- Rabindranath Tagore"},
				{"7","“Nature does nothing uselessly.” -Aristotle"},
				{"7","Flowers are the sweetest things God ever made and forgot to put a soul into.- Henry Ward Beecher"},
				{"7","Spring is nature's way of saying, 'Let's party!'- Robin Williams"},
				{"7","“I believe in God, only I spell it Nature.” - Frank Lloyd Wright"},
				{"7","Autumn is a second spring when every leaf is a flower.- Albert Camus "},
				{"7","Sunshine is delicious, rain is refreshing, wind braces us up, snow is exhilarating; there is really no such thing as bad weather, only different kinds of good weather.- John Ruskin"},
				{"7","Look deep into nature, and then you will understand everything better.- Albert Einstein"},};
		
		for(int j=0;j<quotes.length;j++)
		{
					
			Quotes quote=new Quotes(j,Integer.parseInt(quotes[j][0]),0,quotes[j][1]);
			qvalues.put(Q_KEY_ID, quote.getID());
			qvalues.put(Q_KEY_CAT_ID,quote.getCAT_ID());
			qvalues.put(Q_KEY_TYPE,quote.getTYPE());
			qvalues.put(Q_KEY_QUOTE, quote.getQuote()); // Quote
			db.insert(TABLE_QUOTES, null, qvalues);
		}
			
		//new MainActivity().CopyAssets(db);
		//ApplicationContext;
		//CopyAssets(ctx, db);
		//db.close(); // Closing database connection
		Log.d("Successful Installtion","Install Routine Completed");
	//	return true;
			
	}
	
	 public void CopyAssets(Context context,SQLiteDatabase db) {
		 
		// Log.d(" Application Context",context.getPackageName()+ " Application Context" +context.toString());
		    AssetManager assetManager = ctx.getAssets();
		    String[] folders={"Amazing","Anniversary","Attitude","Beauty","Birthday","Brainy","Communication","Education","Friendship","God","Happiness","Inspirational","Hardwork","Poetry","Science"};
		    String[] files=null;
		   for( String s : folders)
		   {
		    try {
		    	//DatabaseHandler db=new DatabaseHandler(context);
		    	
		        files = assetManager.list(s);
		    } catch (IOException e) {
		        Log.e("tag", e.getMessage());
		    }

		    for(String filename : files) {
		        Log.d("File name => ",filename);
		        InputStream in = null;
		        OutputStream out = null;
		        try {
		          in = assetManager.open(s+"/"+filename);   // if files resides inside the "Files" directory itself
		          out = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+s+"/" + filename);
		          copyFile(in, out);
		          in.close();
		          in = null;
		          out.flush();
		          out.close();
		          out = null;
		        } catch(Exception e) {
		            Log.e("tag", e.getMessage());
		        }
		    }
		   }
		}
		private void copyFile(InputStream in, OutputStream out) throws IOException {
		    byte[] buffer = new byte[1024];
		    int read;
		    while((read = in.read(buffer)) != -1){
		      out.write(buffer, 0, read);
		    }
		}
	
	@Override
    public synchronized void close() {

    if (db != null)
        db.close();

    super.close();

    }
	
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
		// Create tables again
		
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new Quote
	void addQuote(Quotes quote) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Q_KEY_CAT_ID,quote.getCAT_ID());
		values.put(Q_KEY_TYPE,quote.getTYPE());
		values.put(Q_KEY_QUOTE, quote.getQuote()); // Quote
	
		
		// Inserting Row
		db.insert(TABLE_QUOTES, null, values);
		Log.d("New Quote Added",quote.getQuote());
		//db.close(); // Closing database connection
	}

	// Getting single contact
	Quotes getQuote(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_QUOTES, new String[] { Q_KEY_ID,
				Q_KEY_CAT_ID,Q_KEY_TYPE, Q_KEY_QUOTE }, Q_KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Quotes quote = new Quotes(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3));
		// return contact
		return quote;
	}
	
	// Getting single contact
		Quotes getQuote(int id,int type) {
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(TABLE_QUOTES, new String[] { Q_KEY_ID,
					Q_KEY_CAT_ID, Q_KEY_TYPE, Q_KEY_QUOTE }, Q_KEY_ID + "=? "+ Q_KEY_TYPE + "=?",
					new String[] { String.valueOf(id),String.valueOf(type) }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();

			Quotes quote = new Quotes(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3));
			// return contact
			return quote;
		}
		
		// Getting single contact
				Quotes getRandomTextQuote(int cat) {
					
					 Quotes quote=null;
					//int i=getQuotesCount(cat,0);
					String countQuery = "SELECT  * FROM " + TABLE_QUOTES +" where " + Q_KEY_CAT_ID + " = " 
					+ String.valueOf(cat) + " and " + Q_KEY_TYPE + " = " + String.valueOf(0) ;
					Log.d("Quotes Count", countQuery);
					
					SQLiteDatabase db = this.getReadableDatabase();
					Cursor cursor = db.rawQuery(countQuery, null);
					// return count
					//int i=0;
					if(!cursor.isClosed()&& cursor.moveToFirst())
					{					
					Log.d("Quotes Count","Cursor not null");
					int i=cursor.getCount();
					
					int random=(int)new Random().nextInt(i);
					
					Log.d(TAG,  " random " +random+" " + " i val " + i);
					
					cursor.moveToPosition(random);
					  quote = new Quotes(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3));
					cursor.close();
					}
					
	
					
					//
					//SQLiteDatabase db = this.getReadableDatabase();
					/*String query = "SELECT  * FROM " + TABLE_QUOTES +" where " + Q_KEY_CAT_ID + " = " 
							+ String.valueOf(cat) + " and " + Q_KEY_TYPE + " = " + String.valueOf(0) ;Log.d(TAG, query + String.valueOf(random) +String.valueOf(cat) );
					Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(random),String.valueOf(cat)});
					Log.d(TAG,String.valueOf(random));
					if(!ccursor.isClosed() && ccursor.moveToFirst())
						ccursor.moveToPosition(random-1);*/
						
				  //  quote = new Quotes(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3));
					// return contact
					
					return quote;
					
				}
	
		
		
	// Getting All Quotes
	public List<Quotes> getAllQuotes() {
		List<Quotes> quotesList = new ArrayList<Quotes>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUOTES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Quotes quote = new Quotes();
				quote.setID(Integer.parseInt(cursor.getString(0)));
				quote.setCAT_ID(Integer.parseInt(cursor.getString(1)));
				quote.setTYPE(Integer.parseInt(cursor.getString(2)));
				quote.setQuote(cursor.getString(3));
				// Adding contact to list
				quotesList.add(quote);
			} while (cursor.moveToNext());
		}

		// return quote list
		return quotesList;
	}

	// Adding new Category
	void addCategory(Categories category) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		ContentValues values = new ContentValues();
		values.put(C_KEY_ID,category.getID());
		values.put(C_KEY_CATEGORY, category.getCategory()); // Quote	
		
		db.insert(TABLE_CATEGORIES, null, values);
		
		Log.d("Category Added", category.getCategory());
		//db.close(); // Closing database connection
	}

	// Getting single contact
	Categories getCategory(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CATEGORIES, new String[] { C_KEY_ID,
				 C_KEY_CATEGORY }, C_KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Categories category = new Categories(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
		// return contact
		return category;
	}
	
	int getCategoryID(String cat) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CATEGORIES, new String[] { C_KEY_ID,
				 C_KEY_CATEGORY }, C_KEY_CATEGORY + "=?",
				new String[] { String.valueOf(cat) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Categories category = new Categories(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
		// return contact
		return category.getID();
	}
	
	//getting All Categories
	public List<Categories> getAllCategories() {
		List<Categories> catsList = new ArrayList<Categories>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES;

		SQLiteDatabase db = this.getReadableDatabase();
	//	Log.d("from get all quotes",db.findEditTable(TABLE_CATEGORIES).toString() );
		//Log.d("from get all quotes",db. );
		Cursor cursor = db.rawQuery(selectQuery, null);
		//Log.d("from get all quotes",cursor.getColumnCount()+"" );
		
		

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
			//	Log.d("from get all quotes",cursor.getString(0) );
				Categories cats = new Categories();
				cats.setID(Integer.parseInt(cursor.getString(0)));
				cats.setCategory(cursor.getString(1));
				
				// Adding contact to list
				catsList.add(cats);
			} while (cursor.moveToNext());
		}

		// return quote list
		return catsList;
	}

	
	// Updating single quote
	public int updateQuote(Quotes quote) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
	    values.put(Q_KEY_CAT_ID, quote.getCAT_ID());
	    values.put(Q_KEY_QUOTE, quote.getTYPE());
		values.put(Q_KEY_QUOTE, quote.getQuote());

		// updating row
		return db.update(TABLE_QUOTES, values, C_KEY_ID + " = ?",
				new String[] { String.valueOf(quote.getID()) });
	}

	// Deleting single quote
	public void deleteQuote(Quotes quote) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_QUOTES, C_KEY_ID + " = ?",
				new String[] { String.valueOf(quote.getID()) });
		//db.close();
	}


	// Getting quote Count
	public int getQuotesCount(int cat,int type) {
		String countQuery = "SELECT  * FROM " + TABLE_QUOTES +" where " + Q_KEY_CAT_ID + " = " + String.valueOf(cat) + " and " + Q_KEY_TYPE + " = " + String.valueOf(type) ;
		Log.d("Quotes Count", countQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cc=cursor.getCount();
		
		if (cursor.moveToFirst()) {
			do {
			//	Log.d("from get all quotes",cursor.getString(0) );
				
			
				Log.d("quotes" ,cursor.getString(1) + "  " +cursor.getString(3));
				
				// Adding contact to list
				//catsList.add(cats);
			} while (cursor.moveToNext());
		}
		cursor.close();

		// return count
		return cc;
	}

}
