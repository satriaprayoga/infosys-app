package com.infosys.webclient.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.webclient.dto.BookingDTO;
import com.midtrans.httpclient.error.MidtransError;
import com.midtrans.service.MidtransCoreApi;

@Service
public class PaymentService {

	@Autowired
	private MidtransCoreApi midtransCoreApi;
	
	public Map<String,Object> chargeBankTransfer(BookingDTO booking) {
		try {
			Map<String, Object> chargeParams=chargesPayload(booking);
			JSONObject result=midtransCoreApi.chargeTransaction(chargeParams);
			System.out.print(result);//{"status_message":"Success, Bank Transfer transaction is created","transaction_id":"65b8bb66-3402-4b92-b3c8-2e0bfe8afb7c","fraud_status":"accept","payment_type":"bank_transfer","transaction_status":"pending","status_code":"201","transaction_time":"2020-05-30 01:57:32","currency":"IDR","merchant_id":"G548959218","gross_amount":"500000.00","va_numbers":[{"bank":"bca","va_number":"59218831069"}],"order_id":"IB-hftxfm"}//	
			return result.toMap();
		} catch (MidtransError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	private Map<String,Object> chargesPayload(BookingDTO booking){
		Map<String, Object> chargeParams=new HashMap<>();
		Map<String, String> transactionDetails = new HashMap<>();
		transactionDetails.put("order_id", booking.getCode());
		transactionDetails.put("gross_amount", booking.getGrossAmount().toString());
		
//		Map<String, Object> custDetail = new HashMap<>();
//        custDetail.put("first_name", booking.getName());
//        custDetail.put("last_name", booking.getName());
//        custDetail.put("email", booking.getEmail());
//        custDetail.put("phone", booking.getPhone());
//        custDetail.put("billing_address", booking.getBillingAddress());
        
//        List<Map<String, String>> items = new ArrayList<>();
//        Map<String,String> item = new HashMap<>();
//        item.put("packageName", booking.getPackageName()+" "+booking.getPackageGroup()+" "+booking.getDestination());
//        item.put("persons", booking.getAdults().toString());
//        item.put("checkin", booking.getCheckin().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//        item.put("checkout", booking.getCheckout().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//        item.put("price", String.valueOf((booking.getGrossAmount()/booking.getAdults())));
     
       // items.add(item);
		Map<String, String> bankTransfer=new HashMap<String, String>();
		bankTransfer.put("bank", "bca");
		
		chargeParams.put("payment_type", "bank_transfer");
		chargeParams.put("transaction_details", transactionDetails);
		chargeParams.put("bank_transfer", bankTransfer);
		//chargeParams.put("customer_details", custDetail);
		//chargeParams.put("item_details", item);
		return chargeParams;
	}
}
