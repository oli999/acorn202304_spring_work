package com.example.hello2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hello2.pc.Computer;
import com.example.hello2.pc.Cpu;
import com.example.hello2.util.Remocon;
import com.example.hello2.util.RemoconImpl;
import com.example.hello2.util.TvRemocon;

/*
 *  어떤 객체를 spring 이 생성해서 관리 할지 설정( bean 설정 )
 *  
 *  xml 문서로 설정하던 bean 설정을  class 기반으로 한다 
 */
@Configuration
public class JavaConfig {
	/*
	 *  이 메소드에서 리턴되는 객체를 spring 이 관리하는 bean 이 되도록 한다.
	 *  아래의 메소드는 xml 문서에서  <bean id="myCar" class="com.example.demo.Car" /> 와 같다 
	 */
	@Bean
	public Car myCar() { // method 의 이름이 bean 의 이름(id) 역활을 한다.
		System.out.println("myCar() 메소드 호출됨");
		Car c1=new Car();
		return c1;
	}
	
	//Remocon 인터페이스 type 이 spring 이 관리하는 bean 이 되도록 설정해 보세요.
	@Bean
	public Remocon myRemocon() { // bean 의 이름은 => myRemocon 
		Remocon r1=new RemoconImpl();
		return r1;
	}
	
	@Bean
	public Remocon tvRemocon() { // bean 의 이름은 => tvRemocon 
		Remocon r1=new TvRemocon();
		return r1;
	}
	
	@Bean
	public Cpu getCpu() {
		return new Cpu();
	}
	
	@Bean
	public Computer myComputer() {
		//생성자에 또다른 Bean 의 참조값을 필요하면 메소드를 호출해서 얻어내면 된다.
		Computer c1=new Computer(getCpu());
		return c1;
	}
	
}






















