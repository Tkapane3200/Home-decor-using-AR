package com.example.arapp3.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arapp3.Fragment.OrderDetailsFragment;
import com.example.arapp3.MainActivity;
import com.example.arapp3.Model.OrderModel;
import com.example.arapp3.R;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    List<OrderModel> orderModelList;
    Context context;

    public OrdersAdapter(List<OrderModel> orderModelList, Context context){
        this.orderModelList = orderModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_row, parent, false);
        return new ViewHolder(view, context);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderModel orderModel = orderModelList.get(position);
        holder.orderId.setText("Order ID: " + orderModel.getOrder_id());

        // convert string to timestamp
        Timestamp timestamp = Timestamp.valueOf(orderModel.getOrder_date());
        // dd/MM/yyyy
        Date date = new Date(timestamp.getTime());
//        String formattedDate = DateFormat.getDateTimeInstance().format(date);
        String formattedDate = DateFormat.getDateInstance().format(date);
        holder.orderDate.setText("Order Date: " + formattedDate);

//        holder.orderDate.setText("Order Date: " + orderModel.getOrder_date());
        holder.orderStatus.setText("Order Status: " + orderModel.getStatus());
        holder.orderTotal.setText("Order Total: " + orderModel.getTotal());

        holder.btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "View Order", Toast.LENGTH_SHORT).show();

                //Collect Order ID in Bundle
                Bundle bundle = new Bundle();
                bundle.putLong("order_id", orderModel.getOrder_id());
                bundle.putDouble("order_total", orderModel.getTotal());
                bundle.putInt("total_items", orderModel.getOrderItems().size());



                //Go to OrderDetailsFragment
                Fragment orderDetailsFragment = new OrderDetailsFragment();
                Context context = v.getContext();
                orderDetailsFragment.setArguments(bundle);

                ((MainActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_container, orderDetailsFragment).addToBackStack(null).commit();



            }
        });


    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView orderId, orderDate, orderStatus , orderTotal;
        Button btnViewOrder;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            orderId = itemView.findViewById(R.id.order_id);
            orderDate = itemView.findViewById(R.id.order_date);
            orderStatus = itemView.findViewById(R.id.order_status);
            orderTotal = itemView.findViewById(R.id.order_total);
            btnViewOrder = itemView.findViewById(R.id.order_details);
        }
    }







//    List<OrderModel> orderModelList;
//    Context context;
//
//    public OrdersAdapter(List<OrderModel> orderModelList, Context context){
//        this.orderModelList = orderModelList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row, parent, false);
//        return new ViewHolder(view, context);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        OrderModel orderModel = orderModelList.get(position);
//        holder.orderId.setText(String.valueOf(orderModel.getId()));
//        holder.orderDate.setText(orderModel.getDate());
//        holder.orderStatus.setText(orderModel.getStatus());
//        holder.orderTotal.setText(String.valueOf(orderModel.getTotal()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return orderModelList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView orderId, orderDate, orderStatus, orderTotal;
//        public ViewHolder(@NonNull View itemView, Context ctx) {
//            super(itemView);
//            context = ctx;
//            orderId = itemView.findViewById(R.id.order_id);
//            orderDate = itemView.findViewById(R.id.order_date);
//            orderStatus = itemView.findViewById(R.id.order_status);
//            orderTotal = itemView.findViewById(R.id.order_total);
//        }
//    }
}
