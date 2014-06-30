package MySQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoSQLiteHelper extends SQLiteOpenHelper {

	private static String DATABASE_NAME = "fidelitat.db"; // data base name
	private static String TABLE_SHOPS = "shops"; // table playlist name
	private static String TABLE_CARDS = "cards";
	private static String TABLE_CARD_STAMPT = "card_stampt";
	private static String TABLE_TYPE_SHOPS = "type_shops";

	public TodoSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	/**
	 * Too create Table in sqlite
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE " + TABLE_TYPE_SHOPS
				+ "(markerTypeId INTEGER, markerTypeName TEXT NOT NULL, description TEXT, "
				+ "icon TEXT, status TEXT, PRIMARY KEY(markerTypeId));");

		db.execSQL("CREATE TABLE " + TABLE_SHOPS
				+ "(shopId INTEGER, shopName TEXT NOT NULL, description TEXT, "
				+ "typeId INTEGER, longitude INTEGER, latitude INTEGER, address TEXT, email TEXT, "
				+ "web TEXT, telephone TEXT, cellphone TEXT, zipCode TEXT, city TEXT, coverSmallUrl TEXT, "
				+ "coverBigUrl TEXT, status TEXT, facebookUrl TEXT, twitterUrl TEXT, PRIMARY KEY (shopId), FOREIGN KEY(typeId) REFERENCES "
				+ TABLE_TYPE_SHOPS + " (markerTypeId));");

		// db.execSQL("CREATE TABLE " + TABLE_CARD_STAMPT +
		// "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);");
		//
		// db.execSQL("CREATE TABLE " + TABLE_RELACTION_PLAY_SONG +
		// " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
		// + " id_playlist INTEGER, id_song INTEGER, " +
		// " FOREIGN KEY(id_playlist) REFERENCES "
		// + TABLE_PLAYLIST + "(id), " + "FOREIGN KEY(id_song) REFERENCES " +
		// TABLE_SONG
		// + "(id));");
	}

	/**
	 * DROP table if exists
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		// DROP table
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE_SHOPS);
		// db.execSQL("DROP TABLE IF EXISTS " + TABLE_RELACTION_PLAY_SONG);

		onCreate(db);
	}

}
