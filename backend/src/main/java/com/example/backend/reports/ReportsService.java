package com.example.backend.reports;

import com.example.backend.payment.MpesaPayment;
import com.example.backend.payment.MpesaPaymentRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ReportsService {
    @Autowired
    private MpesaPaymentRepository repository;


    public byte[] generateTransactionBarChart(List<MpesaPayment> mpesaPayments) throws IOException{
        Map<String,Double> map = new TreeMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (MpesaPayment p: mpesaPayments){
            String formattedDate = (p.getDate() != null)?simpleDateFormat.format(p.getDate()):"N/A";
            //Date date = Date.valueOf(simpleDateFormat.format(p.getDate()));
            map.put(formattedDate,map.getOrDefault(formattedDate,0.0)+p.getAmount());
        }
        DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
        for (Map.Entry<String,Double> entry : map.entrySet()){
            defaultCategoryDataset.addValue(entry.getValue(),"Total Amount", entry.getKey());
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Daily Mpesa Transactions",
                "Date",
                "Amount (KES)",
                defaultCategoryDataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        BufferedImage bufferedImage = barChart.createBufferedImage(800,400);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"png",byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    public List<MpesaPayment> findAll() {
        return repository.findAll();
    }
}
