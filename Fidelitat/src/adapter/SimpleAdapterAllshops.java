package adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.digiteix.models.Shop;
import com.fidelitat.R;

public class SimpleAdapterAllshops extends ArrayAdapter<Shop> {

	private ArrayList<Shop> itemList;
	private Context context;

	public SimpleAdapterAllshops(Context context2, ArrayList<Shop> itemList) {
		super(context2, R.layout.list_view_shops_layout, itemList);
		this.itemList = itemList;
		this.context = context2;
	}

	public int getCount() {
		if (itemList != null)
			return itemList.size();
		return 0;
	}

	public Shop getItem(int position) {
		if (itemList != null)
			return itemList.get(position);
		return null;
	}

	public long getItemId(int position) {
		if (itemList != null)
			return itemList.get(position).hashCode();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_view_shops_layout, null);
		}

		Shop c = itemList.get(position);

		//Get artist name and surname
		TextView shopName = (TextView) v.findViewById(R.id.txtAdaptarShopNameList);
		TextView shopDesc = (TextView) v.findViewById(R.id.txtAdaptarShopDescripcionList);
		ImageView imgTyp = (ImageView) v.findViewById(R.id.imgAdaptarSelectShopType);
		ImageView imgShopLogo = (ImageView) v.findViewById(R.id.imgAdaptarShopListLogo);
		
		shopName.setText(c.getShopName());
		shopDesc.setText(c.getAddress());
//		imgTyp.setImageBitmap(bm);
//		imgShopLogo

		return v;

	}

	public List<Shop> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Shop> itemList) {
		this.itemList = itemList;
	}

}
