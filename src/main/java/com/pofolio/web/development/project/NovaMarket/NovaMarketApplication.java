package com.pofolio.web.development.project.NovaMarket;

import com.pofolio.web.development.project.NovaMarket.entity.*;
import com.pofolio.web.development.project.NovaMarket.repository.CartItemRepository;
import com.pofolio.web.development.project.NovaMarket.repository.WishlistRepository;
import com.pofolio.web.development.project.NovaMarket.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class NovaMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(NovaMarketApplication.class, args);

//		// create session factory
//		SessionFactory factory = new Configuration()
//				.configure("hibernate.cfg.xml")
//				.addAnnotatedClass(Instructor.class)
//				.addAnnotatedClass(InstructorDetail.class)
//				.addAnnotatedClass(Course.class)
//				.addAnnotatedClass(Review.class)
//				.addAnnotatedClass(Student.class)
//				.buildSessionFactory();
//
//		// create session
//		Session session = factory.getCurrentSession();
//
//		try {
//
//			// start a transaction
//			session.beginTransaction();
//
//			// get the student from database
//			int studentId = 1;
//			Student tempStudent = session.get(Student.class, studentId);
//
//			System.out.println("\nLoaded student: " + tempStudent);
//			System.out.println("Courses: " + tempStudent.getCourses());
//
//			// commit transaction
//			session.getTransaction().commit();
//
//			System.out.println("Done!");
//		}
//		finally {
//
//			// add clean up code
//			session.close();
//
//			factory.close();
//		}
	}

	@Bean
	CommandLineRunner loadData(OrderService orderService, ProductService productService, OrderItemService orderItemService, UserService userService, WishlistService wishlistService, CartItemService cartItemService, CartItemRepository cartItemRepository) {
		return (args) -> {

//			Order order1 = new Order();
//			Order order2 = new Order();
//
//			Product product1 = new Product();
//			Product product2 = new Product();
//			Product product3 = new Product();
//			Product product4 = new Product();
//			Product product5 = new Product();
//
//			Order dbOrder1 = orderService.saveOrder(order1);
//			Order dbOrder2 = orderService.saveOrder(order2);
//
//			Product dbProduct1 = productService.saveProduct(product1);
//			Product dbProduct2 = productService.saveProduct(product2);
//			Product dbProduct3 = productService.saveProduct(product3);
//			Product dbProduct4 = productService.saveProduct(product4);
//			Product dbProduct5 = productService.saveProduct(product5);
//
//			//OrderId 1 contains ProductIds 1, 3 and 5
//
//			OrderItem orderDetails1 = new OrderItem(null,dbOrder1,dbProduct1,null,1000,null);
//			OrderItem dbod1 = orderItemService.saveOrderItem(orderDetails1 );
//
//			OrderItem orderDetails2 = new OrderItem(null,dbOrder1,dbProduct3,null,1000,null);
//			OrderItem dbod2 = orderItemService.saveOrderItem(orderDetails2 );
//
//			OrderItem orderDetails3 = new OrderItem(null,dbOrder1,dbProduct5,null,1000,null);
//			OrderItem dbod3 = orderItemService.saveOrderItem(orderDetails3 );
//
//
//			//OrderId 2 contains ProductIds 2 and 4
//
//			OrderItem orderDetails4 = new OrderItem(null,dbOrder2,dbProduct2,LocalDate.now(),1000,"Pending");
//			OrderItem dbod4 = orderItemService.saveOrderItem(orderDetails4 );
//
//			OrderItem orderDetails5 = new OrderItem(null,dbOrder2,dbProduct4,LocalDate.of(2022,2,12),1000,"Pending");
//			OrderItem dbod5 = orderItemService.saveOrderItem(orderDetails5 );


			//{{{{{TEST}}}}}

			//RegisterUser
			//Customer registeredUser = userService.registerUser(new Customer(1L,"Kyaw","Kyaw","kyawgyi@gmail.com","123456","East Dagon", "Yangon", "Yangon", "123131", "09955271558", LocalDate.of(2022,2,12)));

			//

			//Add rows in product table with loop
//				List<Product> products = IntStream.rangeClosed(1, 200)
//						.mapToObj(i -> new Product("productname" + i, "description" + i, new Random().nextLong(10), new Random().nextDouble() * 10))
//						.collect(Collectors.toList());
//				productService.saveProductList(products);

			//

			//SearchProduct
//			List<Product> products = productService.searchProduct("ple");
//			if(products.size()>0)
//			products.forEach((n) -> System.out.println(n));
//			else
//				System.out.println("Item Not Found");

			//FilterByPriceRange
//			double startPrice = 10;
//			double endPrice = 20000;
//
//			if(startPrice>endPrice && startPrice>=0)
//			{
//				Page<Product> products = productService.filterByPriceRange(startPrice,endPrice);
//				if(products.isEmpty())
//					products.forEach((n) -> System.out.println(n));
//				else
//					System.out.println("Item Not Found");
//			}

//			System.out.println(wishlistService.findWishlistById(1L).get());


			///////////////////////////////////Fixing ERROR/////

//			Long productId = 1L;
//			Long customerId = 1L;
//
//			Customer savedCustomer = userService.registerUser(userService.findUserById(customerId).get());
//
//			Wishlist wishlist = new Wishlist();
//			wishlist.setCustomer(savedCustomer);
//			wishlist.setCreatedDate(LocalDate.now());
//
//			Wishlist savedWishlist = wishlistService.addToWishlist(wishlist);
//
//			System.out.println("Saved Wish List" + savedWishlist.toString());


//			Product savedProduct = productService.saveProduct(new Product());
//			Wishlist savedWishlist = wishlistService.addToWishlist(new Wishlist());

//			System.out.println( "Hello" + cartItemService.getCartItemById(2L));

			/////////

//			List<CartItem> cartItems = cartItemRepository.findCartItemsByCartId(4L);
//        	if (cartItems.size() > 0){
//
//				//Check CartItem Child Table
//				System.out.println(cartItems.get(0).getProduct());
//
//
//				//Check Cart Parent Table
//				Cart savedCart = cartItems.get(0).getCart();
//				if (savedCart != null)
//					System.out.println("Yare " + savedCart);
//				else
//					System.out.println("Muda");
//			}

			Customer registeredUser = userService.registerUser(new Customer(1L,"Kyaw","Kyaw","kyawgyi@gmail.com","123456","East Dagon", "Yangon", "Yangon", "123131", "09955271558", LocalDate.of(2022,2,12)));

			Cart cart1 = new Cart(1L,registeredUser,LocalDate.of(2022,2,12),"Completed");

			Product product1 = new Product("productName", "descript", null, 100.0);

			CartItem cartItem = new CartItem(1L, cart1, product1, 2, 100.0, 100.0);
		};
	}

}
