package wiyi.org.elvheaderandfooter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xing on 9/25/15.
 */
public class OrderAdapter extends BaseExpandableListAdapter {
    private List<Order> orderList ;
    private LayoutInflater inflater ;
    private View group ;

    public OrderAdapter(List<Order> orderList,Context context) {
        this.orderList = orderList ;
        inflater = LayoutInflater.from(context) ;
        group = new FrameLayout(context) ;
    }

    @Override
    public int getGroupCount() {
        return orderList == null ? 0 : orderList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<OrderItem> children = orderList.get(groupPosition).getItems() ;
        int size = children.size() ;
        return size == 0 ? 0 : size + 2 ;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return orderList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return orderList.get(groupPosition).getItems().get(childPosition) ;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_group,null) ;
        }

        TextView group = (TextView) convertView.findViewById(R.id.tvGroup);
        group.setText("订单:" + (groupPosition + 1));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        do {
            if (childPosition > 0 && childPosition < getChildrenCount(groupPosition) - 1) {
                OrderItem item = (OrderItem) getChild(groupPosition,childPosition - 1);
                ChildHolder holder ;
                if (convertView == null) {
                    holder = new ChildHolder();
                    convertView = inflater.inflate(R.layout.view_order_item,null) ;
                    TextView tvItemName = (TextView) convertView.findViewById(R.id.tvItemName);
                    TextView tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
                    TextView tvQuantity = (TextView) convertView.findViewById(R.id.tvQuantity);

                    holder.itemName = tvItemName ;
                    holder.price = tvPrice ;
                    holder.quantity = tvQuantity ;
                    convertView.setTag(holder);
                } else {
                    holder = (ChildHolder) convertView.getTag();
                }

                holder.itemName.setText(item.getName());
                holder.price.setText("￥" + item.getPrice());
                holder.quantity.setText(String.valueOf(item.getQuantity()));

                break ;
            }

            Order order = (Order) getGroup(groupPosition);
            if (childPosition == 0) {
                convertView = inflater.inflate(R.layout.view_order_header,null) ;
                TextView header = (TextView) convertView.findViewById(R.id.tvHeader);
                header.setText("共" + order.getItems().size() + "份美食");

                break ;
            }

            if (childPosition == getChildrenCount(groupPosition) - 1) {
                convertView = inflater.inflate(R.layout.view_order_footer,null) ;
                TextView footer = (TextView) convertView.findViewById(R.id.tvFooter);
                footer.setText("总计:￥" + order.getTotalPrices() );

                break ;
            }
        } while(false) ;

        return convertView;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        boolean isHeadOrFoot = childPosition == 0 || (childPosition == getChildrenCount(groupPosition) - 1) ;
        //如果是Header或者Footer,返回一个负数,我们返回ListView默认值
        if (isHeadOrFoot) {
            return ListView.ITEM_VIEW_TYPE_HEADER_OR_FOOTER ;
        }

        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ChildHolder {
        public TextView itemName ;
        public TextView price ;
        public TextView quantity ;
    }
}
