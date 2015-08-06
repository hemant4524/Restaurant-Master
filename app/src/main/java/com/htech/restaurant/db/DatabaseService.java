package com.htech.restaurant.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.htech.restaurant.common.AppConstant;
import com.htech.restaurant.vos.MainMenu;
import com.htech.restaurant.vos.SubMenu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseService extends SQLiteOpenHelper
{

	private static DatabaseService databaseServiceInstance;
	private SQLiteDatabase myDataBase;
	private final Context myContext;

	// Table Home
	private static String TABLE_WAITER = "Waiter";
	private static String TABLE_MAINMENU = "main_menu";
	private static String TABLE_SUBMENU = "sub_menu";
	private static String TABLE_ORDER_MASTER = "order_master";
	private static String TABLE_ORDER_TRANSACTION = "order_transaction";

	// private static Context myContext;
	private DatabaseService(Context context) throws IOException
	{
		super(context, AppConstant.DB_NAME, null, 1);

		this.myContext = context;

		// CreateNew Database
		this.createDataBase();

		// Open Database
		this.openDataBase();
	}

	public static DatabaseService getInstance(Context c) throws IOException
	{

		if (databaseServiceInstance == null)
		{
			databaseServiceInstance = new DatabaseService(c);
		}

		return databaseServiceInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WAITER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAINMENU);
	}

	public void createDataBase() throws IOException
	{

		Boolean dbExist = this.checkDataBase();

		if (dbExist)
		{
			// do nothing - database already exist
			Log.v("DB Exist", "YES");
		}
		else
		{

			Log.v("DB Exist", "NO");

			this.getReadableDatabase();
			try
			{
				// Log.v("Copy DB into System", "YES");
				this.copyDataBase();
			}
			catch (IOException e)
			{
				// Log.v("Copy DB into System", "Error");
				throw new Error("Error copying database");
			}
			finally
			{
				// Open Database
				this.openDataBase();
				myDataBase.setVersion(AppConstant.DB_VERSION);
			}
		}
	}

	public Boolean checkDataBase()
	{

		File dbFile = new File(AppConstant.DB_PATH + AppConstant.DB_NAME);
		// System.out.println("Database File Path : " + dbFile);

		if (dbFile.exists())
		{
			// Open Database
			this.openDataBase();
			// Log.v("databse old version"+myDataBase.getVersion()
			// ,"new version"+ CommonUtilities.DB_VERSION);
			if (myDataBase.getVersion() == AppConstant.DB_VERSION)
			{
				return true;
			}
			else
			{

				onUpgrade(myDataBase, myDataBase.getVersion(), AppConstant.DB_VERSION);
				return false;
			}
		}
		else
		{

			return false;
		}

	}

	public void openDataBase() throws SQLException
	{

		// Open the database
		String myPath = AppConstant.DB_PATH + AppConstant.DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

	}

	@Override
	public synchronized void close()
	{
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	public void copyDataBase() throws IOException
	{

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(AppConstant.DB_NAME);
		// System.out.println("System Path to Copy DB : " + myInput);

		// Path to the just created empty db
		String outFileName = AppConstant.DB_PATH + AppConstant.DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0)
		{
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	// /**
	// * Insert waiter list into waiter database table
	// * @param mWaiterList
	// * @return true when data insert successfully other wise false
	// */
	// public Boolean insertWaiterInfoInDb(ArrayList<Waiter> mWaiterList) {
	// if (mWaiterList == null || mWaiterList.size() == 0) {
	// return false;
	// } else
	// {
	//
	//
	//
	// // INSERT INTO "main"."waiter" ("name","city","address","latitude","longitude","phone") VALUES (?1,?2,?3,?4,?5,?6)
	//
	// try {
	//
	// for (int i = 0; i < mWaiterList.size(); i++) {
	//
	// Waiter waiter = mWaiterList.get(i);
	// String sql = ("INSERT INTO " + TABLE_WAITER + "("
	// + KeyValueStore.KEY_WAITER_NAME + ", "
	// + KeyValueStore.KEY_WAITER_CITY + ", "
	// + KeyValueStore.KEY_WAITER_ADDRESS
	// + ") VALUES(" + "'"
	// + waiter.getName()
	// + "', " + "'"
	// + waiter.getCity()
	// + "', " + "'"
	// + waiter.getAddress() + "'); ");
	//
	// Log.v("Insert SQL HOme Screen", sql);
	// // Execute Query
	// myDataBase.beginTransaction();
	// myDataBase.execSQL(sql);
	// myDataBase.setTransactionSuccessful();
	// myDataBase.endTransaction();
	// }
	//
	// return true;
	//
	// } catch (Exception SQLException) {
	// myDataBase.endTransaction();
	// Log.e("SQL Error", SQLException.toString());
	// return false;
	// }
	// }
	// }

	/**
	 * Get main menu from data base
	 *
	 * @return list of MainMenu
	 */
	public ArrayList<MainMenu> getMainMenu()
	{
		ArrayList<MainMenu> menus = new ArrayList<>();
		String selectQuery = "SELECT  * FROM " + TABLE_MAINMENU;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst())
		{
			do
			{
				MainMenu menu = new MainMenu();
				menu.setId(cursor.getInt(0));
				menu.setCatName(cursor.getString(1));
				menu.setIcon(cursor.getString(2));
				menus.add(menu);
			} while (cursor.moveToNext());
		}
		db.close();

		return menus;
	}

	/**
	 * Get sub menu from data base
	 *
	 * @return list of SubMenu
	 */
	public ArrayList<SubMenu> getSubMenu(int pMainMenuId)
	{
		ArrayList<SubMenu> subMenus = new ArrayList<>();
		String selectQuery = "SELECT  * FROM " + TABLE_SUBMENU + " where menu_id=" + (pMainMenuId + 1);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst())
		{
			do
			{
				SubMenu menu = new SubMenu();
				menu.setId(cursor.getInt(0));
				menu.setSubCatName(cursor.getString(1));
				menu.setSubIcon(cursor.getString(2));
				menu.setPrice(cursor.getInt(3));
				subMenus.add(menu);
			} while (cursor.moveToNext());
		}
		db.close();

		return subMenus;
	}

    /**
     * Add new record in transaction table
     * @param orderid
     * @param mainMenuId
     * @param submenu_id
     * @return
     */
	public boolean addOrderTransaction(String orderid, String mainMenuId, String submenu_id)
	{
		int total = 0;
		try
		{

			// Find last inserted record id
			Cursor cursor = myDataBase.rawQuery("SELECT " + KeyValueStore.KEY_ORDER_TRANSACTION_QTY + " FROM " + TABLE_ORDER_TRANSACTION + " WHERE "
					+ KeyValueStore.KEY_ORDER_ID + "=" + orderid + " AND " + KeyValueStore.KEY_ORDER_TRANSACTION_SUB_MENU_ID + "=" + submenu_id, null);

			if (cursor.moveToFirst())
			{
				total = cursor.getInt(0);
			}
			// total = (cursor.moveToFirst() ? cursor.getInt(0) : 0);


			// Find total is zero then create new record otherwise do nothing
			if (total == 0)
			{
				String sql = ("INSERT INTO " + TABLE_ORDER_TRANSACTION + "(" + KeyValueStore.KEY_ORDER_TRANSACTION_ORDER_ID + ", "
						+ KeyValueStore.KEY_ORDER_TRANSACTION_MAIN_MENU_ID + ", " + KeyValueStore.KEY_ORDER_TRANSACTION_SUB_MENU_ID + ", " + KeyValueStore.KEY_ORDER_TRANSACTION_QTY + ") VALUES("
						+ "'" + orderid + "', " + "'" + mainMenuId + "', " + "'" + submenu_id + "','1' ); ");
				Log.v("addOrderTransaction ", sql);
				// Execute Query
				myDataBase.beginTransaction();
				myDataBase.execSQL(sql);
				myDataBase.setTransactionSuccessful();
				myDataBase.endTransaction();
			}
			else
			{
				// Do something
				Log.v("addOrderTransaction ", "else total:"+total);
			}

			return true;
		}
		catch (Exception SQLException)
		{
				Log.d("addOrderTransa Error:", "" + SQLException.getMessage());
		}
		return false;
	}

	public boolean updateOrderTransaction(String orderId, String subMenuId, int operationType)
	{

		try
		{
			// Find last inserted record id
			Cursor cursor = myDataBase.rawQuery("SELECT " + KeyValueStore.KEY_ORDER_TRANSACTION_QTY + " FROM " + TABLE_ORDER_TRANSACTION + " WHERE "
					+ KeyValueStore.KEY_ORDER_ID + "=" + orderId + " AND " + KeyValueStore.KEY_ORDER_TRANSACTION_SUB_MENU_ID + "=" + subMenuId, null);
			int total = (cursor.moveToFirst() ? cursor.getInt(0) : 0);

			if (operationType == 1)
			{
				total = total + 1;
			}
			else
			{
				if(total > 0)
				{
					total = total - 1;
				}

			}

			String strSQL = "UPDATE " + TABLE_ORDER_TRANSACTION + " SET " + KeyValueStore.KEY_ORDER_TRANSACTION_QTY + " = " + total + " WHERE "
					+ KeyValueStore.KEY_ORDER_ID + "=" + orderId + " AND " + KeyValueStore.KEY_ORDER_TRANSACTION_SUB_MENU_ID + "=" + subMenuId;
			// Execute Query
			myDataBase.beginTransaction();
			myDataBase.execSQL(strSQL);
			myDataBase.setTransactionSuccessful();
			myDataBase.endTransaction();
			Log.v("UPDATE record done", strSQL);
			return true;
		}
		catch (Exception SQLException)
		{
			Log.v("UPDATE record error:", SQLException.getMessage());
			SQLException.printStackTrace();

		}
		return false;
	}

	/**
	 * create entry in order master table
	 *
	 * @return
	 */
	public int createOrder(String datatime, String tableid, String waiter_id)
	{
		int id = 0;
		try
		{
			String sql = ("INSERT INTO " + TABLE_ORDER_MASTER + "(" + KeyValueStore.KEY_ORDER_DATETIME + ", " + KeyValueStore.KEY_ORDER_TABLE_ID
					+ ", " + KeyValueStore.KEY_ORDER_WAITER_ID + ") VALUES(" + "'" + datatime + "', " + "'" + tableid + "', " + "'" + waiter_id + "'); ");
			Log.v("Insert SQL " + TABLE_ORDER_MASTER, sql);
			// Execute Query
			myDataBase.beginTransaction();
			myDataBase.execSQL(sql);
			myDataBase.setTransactionSuccessful();
			myDataBase.endTransaction();

			// Find last inserted record id

			Cursor cursor = myDataBase.rawQuery("SELECT MAX(" + KeyValueStore.KEY_ORDER_ID + ") FROM " + TABLE_ORDER_MASTER, null);
			id = (cursor.moveToFirst() ? cursor.getInt(0) : 0);
		}
		catch (Exception SQLException)
		{
			SQLException.printStackTrace();
		}
		return id;
	}
}