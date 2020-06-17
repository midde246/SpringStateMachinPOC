package com.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.statemachine.demo.util.Events;
import com.statemachine.demo.util.States;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<States, Events> {
	
	@Override
	public void configure(StateMachineStateConfigurer<States, Events> stateConfig) throws Exception {
		stateConfig.withStates()
		 		   .initial(States.BACKLOG)
		 		   .state(States.DEVELOPMENT)
		 		   .state(States.TESTING)
		 		   .end(States.LIVE);
	}
	 
	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		 transitions.withExternal()
		 			.source(States.BACKLOG)
		 			.target(States.DEVELOPMENT)
		 			.event(Events.START_FEATURE)
		 			.and()
		 			.withExternal()
		 			.source(States.DEVELOPMENT)
		 			.target(States.TESTING)
		 			.event(Events.FINISH_FEATURE)
		 			.and()
		 			.withExternal()
		 			.source(States.TESTING)
		 			.target(States.DEVELOPMENT)
		 			.event(Events.QA_REJECTED)
		 			.and()
		 			.withExternal()
		 			.source(States.TESTING)
		 			.target(States.LIVE)
		 			.event(Events.QA_ACCEPTED);
		 
	}
	
	
}
