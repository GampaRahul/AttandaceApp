package com.example.android.attandencecalculator;

        import android.content.Context;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.text.Layout;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
    private   RelativeLayout childRelativeLayout;
    EditText textIn1;
    EditText textIn2;
    Button buttonAdd;
    int count=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
        textIn1 = (EditText)findViewById(R.id.editText);
        textIn2 = (EditText)findViewById(R.id.minval);
        buttonAdd = (Button)findViewById(R.id.add);


    }
    public void onAddField(View v) {
        if(count<100) {


            if (textIn1.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Enter Subject Name!!", Toast.LENGTH_LONG).show();
            } else {
                textIn1.setEnabled(false);
                textIn2.setEnabled(false);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.attandence, null);
                // Add the new row before the add field button.
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
                count++;
                TextView textOut = (TextView) rowView.findViewById(R.id.name);
                textOut.setText(textIn1.getText().toString());
                textOut = (TextView) rowView.findViewById(R.id.minper);
                String s;
                if (textIn2.getText().toString().trim().equals("")) {
                    s="75";
                }else s = textIn2.getText().toString();

                textOut.setText(String.format("Min: %s",s)+"%");
                textOut = (TextView) rowView.findViewById(R.id.hidden);
                textOut.setText(textIn2.getText().toString());
                textIn1.setText("");
                textIn2.setEnabled(true);
                textIn2.setText("");
                textIn1.setEnabled(true);



            }
        }else{
            Toast.makeText(getApplicationContext(), "Cannot Add more than 100 subjects!!", Toast.LENGTH_LONG).show();
        }
    }
    public void onplus(View v)
    {
       childRelativeLayout = (RelativeLayout)v.getParent();
       Button b1 = (Button)childRelativeLayout.findViewById(R.id.plus);
        Button b2 = (Button)childRelativeLayout.findViewById(R.id.minus);
        TextView percent = (TextView)childRelativeLayout.findViewById(R.id.percent);
        TextView add = (TextView)childRelativeLayout.findViewById(R.id.add);
        TextView sub = (TextView)childRelativeLayout.findViewById(R.id.sub);
        TextView txt = (TextView)childRelativeLayout.findViewById(R.id.per);
        TextView hide = (TextView)childRelativeLayout.findViewById(R.id.hidden);
        String s = add.getText().toString();
        String s1 = sub.getText().toString();
        int i = Integer.parseInt(s);
        int j = Integer.parseInt(s1);
        i++;
        double k;
        s1 =hide.getText().toString();
        if (s1.trim().equals("")) {
            k = 75;
        }else {
            k = Double.parseDouble(s1);
        }
        int total = i+j;
        double p = (1.0*i/total)*100;
        s=Integer.toString(i);
        add.setText(s);
        percent.setText(String.format("%.1f",p)+"%");
        if(p>=k) {
            percent.setBackgroundColor(getResources().getColor(R.color.green));
            txt.setText("You are doing good!!!");
        }else
        {
            int days = (((int)k*total)-(100*i))/(100-(int)k);
            percent.setBackgroundColor(getResources().getColor(R.color.red));
            txt.setText(String.format("Attend %d Classes for %.1f",days,k)+"%");

        }

    }
    public void onminus(View v)
    {
        childRelativeLayout = (RelativeLayout)v.getParent();
        TextView percent = (TextView)childRelativeLayout.findViewById(R.id.percent);
        TextView add = (TextView)childRelativeLayout.findViewById(R.id.add);
        TextView sub = (TextView)childRelativeLayout.findViewById(R.id.sub);
        TextView txt = (TextView)childRelativeLayout.findViewById(R.id.per);
        TextView hide = (TextView)childRelativeLayout.findViewById(R.id.hidden);
        String s = add.getText().toString();
        String s1 = sub.getText().toString();
        int i = Integer.parseInt(s);
        int j = Integer.parseInt(s1);
        j++;
        s1 =hide.getText().toString();
        double k = Double.parseDouble(s1);
        int total = i+j;
        double p = (1.0*i/total)*100;
        s=Integer.toString(j);
        sub.setText(s);
        percent.setText(String.format("%.1f",p)+"%");
        if(p<k) {

            int days = (((int)k*total)-(100*i))/(100-(int)k);
            percent.setBackgroundColor(getResources().getColor(R.color.red));
            //txt.setText("Hurry Up!!, Attend few classes");
            txt.setText(String.format("Attend %d Classes for %.1f",days,k)+"%");
        }


    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }
    public void remove( View v)
    {
        if(count==0)
        {
            Toast.makeText(getApplicationContext(), "No Added Subjects To Delete", Toast.LENGTH_SHORT).show();
        }
        for(int i=count;i>=0;i--)
        {
            parentLinearLayout.removeView(findViewById(R.id.attdblock));
        }
        count=0;
    }

}