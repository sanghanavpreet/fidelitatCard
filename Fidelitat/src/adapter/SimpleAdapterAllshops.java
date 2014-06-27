package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.digiteix.models.Shop;
import com.digiteix.models.ShopList;
import com.fidelitat.R;

public class SimpleAdapterAllshops extends ArrayAdapter<Shop> {


	private ShopList itemList;
	private Context context;


	
	// public SimpleAdapterAllshops(Context context, int resource, int
	// textViewResourceId, List<Shop> objects) {
	// super(context, resource, textViewResourceId, objects);
	// // TODO Auto-generated constructor stub
	// }
	//
	// public SimpleAdapterAllshops(ShopListActivity shopListActivity,
	// ArrayList<ShopList> todos) {
	// super(shopListActivity, R.layout.list_view_shops_layout, itemList);
	// this.itemList = itemList;
	// this.context = context;
	// }
	//
	// public SimpleAdapterAllshops(Context context, ArrayList<Shop> itemLista)
	// {
	// super(context, R.layout.list_view_shops_layout);
	// this.itemList = itemLista;
	// this.context = context;
	// }

	public SimpleAdapterAllshops(Context context, ShopList itemList) {
		super(context, R.layout.list_view_shops_layout);
		this.itemList = itemList;
		this.context = context;
	}


	public int getCount() {
		if (itemList != null)
			return itemList.getArrayShop().size();
		return 0;
	}


	public Shop getItem(int position) {
		if (itemList != null)
			return itemList.getArrayShop().get(position);
		return null;
	}

	public long getItemId(int position) {
		if (itemList != null)
			return itemList.getArrayShop().get(position).hashCode();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_view_shops_layout, null);
		}

		Shop c = itemList.getArrayShop().get(position);

		// Get artist name and surname
		TextView shopName = (TextView) v.findViewById(R.id.txtAdaptarShopNameList);
		TextView shopDesc = (TextView) v.findViewById(R.id.txtAdaptarShopDescripcionList);
		ImageView imgTyp = (ImageView) v.findViewById(R.id.imgAdaptarSelectShopType);
		ImageView imgShopLogo = (ImageView) v.findViewById(R.id.imgAdaptarShopListLogo);

		shopName.setText(c.getShopName());
//		shopDesc.setText(c.getAddress());
		// imgTyp.setImageBitmap(bm);
		// imgShopLogo

		return v;

	}

	public ShopList getItemList() {
		return itemList;
	}

	public void setItemList(ShopList itemList) {
		this.itemList = itemList;
	}

}
