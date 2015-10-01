package wiyi.org.elvheaderandfooter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ExpandableListView elvOrder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elvOrder = (ExpandableListView) findViewById(R.id.elvOrder);
        setData();
    }

    private void setData() {
        List<Order> orders = new ArrayList<>() ;
        for (int i=0;i<10;i++) {
            Order order = new Order() ;
            order.setOrderName("order:" + (i+1));
            List<OrderItem> items = new ArrayList<>() ;
            for (int j=0;j<i;j++) {
                OrderItem item = new OrderItem() ;
                item.setName("foot:" + (j+1));
                item.setPrice((j + 1) * 5);
                item.setQuantity((j+1));
                items.add(item) ;
            }
            order.setItems(items);
            orders.add(order) ;
        }

        OrderAdapter adapter = new OrderAdapter(orders,this) ;
        elvOrder.setAdapter(adapter);

        int size = adapter.getGroupCount() ;
        for (int i=0;i<size;i++) {
            elvOrder.expandGroup(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
