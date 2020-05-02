package com.infosys.customer.service.data;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.infosys.customer.domain.Booking;
import com.infosys.customer.domain.BookingStatus;
import com.infosys.customer.domain.Customer;
import com.infosys.customer.domain.PaymentDetails;
import com.infosys.customer.dto.payload.BookingInfoRequest;
import com.infosys.customer.dto.payload.BookingRequest;
import com.infosys.customer.dto.payload.PaymentInfoRequest;
import com.infosys.customer.exceptions.ResourceNotFoundException;
import com.infosys.customer.repository.BookingRepository;
import com.infosys.customer.repository.CustomerRepository;
import com.infosys.customer.service.AbstractBaseService;
import com.infosys.customer.utils.RandomUtils;

@Service
public class BookingService extends AbstractBaseService<Booking, String>{

	private final BookingRepository bookingRepository;
	private final CustomerRepository customerRepository;
	
	public BookingService(BookingRepository bookingRepository,CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.bookingRepository=bookingRepository;
		setRepository(bookingRepository);
	}
	
	@Override
	public Optional<Booking> findOne(String id) {
		// TODO Auto-generated method stub
		return super.findOne(id);
	}
	
	public Booking findOneByCode(String code) {
		return bookingRepository.findOneByCode(code).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException(Booking.class, "code",code);
		});
	}
	public Booking findOneByCustomerAndCode(Customer customer,String code){
		return bookingRepository.findOneByCustomerAndCode(customer, code).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException(Booking.class, "code",code);
		});
	}
	
	public Booking findOneByCustomerAndCodeAndStatus(Customer customer,String code,BookingStatus bookingStatus){
		return bookingRepository.findOneByCustomerAndCodeAndStatus(customer, code, bookingStatus).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException(Booking.class, "code",code);
		});
	}
	
	public List<Booking> findByCustomer(Customer customer){
		return bookingRepository.findByCustomer(customer);
	}
	
	public Booking bookTour(BookingRequest request) {
		Optional<Customer> customer=customerRepository.findById(request.getCustomerId());
		if(customer.isPresent()) {
			Customer c=customer.get();
			Booking booking=new Booking();
			booking.setDestination(request.getDestination());
			booking.setPackageGroup(request.getPackageGroup());
			booking.setPackageId(request.getPackageId());
			booking.setPackageName(request.getPackageName());
			booking.setCheckin(request.getCheckin());
			booking.setCheckout(request.getCheckout());
			booking.setChildren(request.getChildren());
			booking.setAdults(request.getAdults());
			booking.setDay(request.getDay());
			booking.setNight(request.getNight());
			booking.setGrossAmount(request.getGrossAmount());
			booking.setStatus(BookingStatus.CREATED);
			booking.setCustomer(c);
			StringBuffer buffer=new StringBuffer();
			buffer.append("IB-").append(RandomUtils.generateRandomAlphabet(6));
			booking.setCode(buffer.toString());
			return super.create(booking);
		}else {
			throw new ResourceNotFoundException(Customer.class,"id", request.getCustomerId());
		}

	}
	
	public Booking saveBookingInfo(BookingInfoRequest request) {
		Optional<Booking> booking=bookingRepository.findOneByCode(request.getBookingCode());
		if(booking.isPresent()) {
			Booking b=booking.get();
			b.setName(request.getName());
			b.setEmail(request.getEmail());
			b.setPhone(request.getPhone());
			b.setAddress(request.getAddress());
			return super.save(b);
		}else {
			throw new ResourceNotFoundException(Booking.class,"code", request.getBookingCode());
		}
	}
	
	public Booking savePaymentInfo(PaymentInfoRequest request) {
		Optional<Booking> booking=bookingRepository.findOneByCode(request.getBookingCode());
		if(booking.isPresent()) {
			Booking b=booking.get();
			PaymentDetails pd=new PaymentDetails();
			pd.setPaymentType(request.getPaymentType());
			if(pd.getPaymentType().equalsIgnoreCase("cc")) {
				pd.setCardNumber(request.getCardNumber());
				pd.setCvv(request.getCvv());
				pd.setValidUntil(request.getValidUntil());
			}
			pd.setBooking(b);
			b.setPaymentDetails(pd);
			return super.save(b);
		}else {
			throw new ResourceNotFoundException(Booking.class,"code", request.getBookingCode());
		}
	}
}
