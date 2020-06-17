package com.statemachine;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import com.statemachine.demo.util.Events;
import com.statemachine.demo.util.States;

@SpringBootTest
public class StateMachineConfigTest {

	@Autowired
	StateMachineFactory<States, Events> facotry;

	@Test
	public void testNewStateMachine() {
		StateMachine<States, Events> sm = facotry.getStateMachine(UUID.randomUUID());
		sm.start();
		System.out.println(sm.getState().toString());

		sm.sendEvent(Events.START_FEATURE);
		System.out.println(sm.getState().toString());

		sm.sendEvent(Events.FINISH_FEATURE);
		System.out.println(sm.getState().toString());
		
		sm.sendEvent(Events.QA_REJECTED);
		System.out.println(sm.getState().toString());
		
		sm.sendEvent(Events.FINISH_FEATURE);
		System.out.println(sm.getState().toString());
		
		sm.sendEvent(Events.QA_ACCEPTED);
		System.out.println(sm.getState().toString());
	}
}
