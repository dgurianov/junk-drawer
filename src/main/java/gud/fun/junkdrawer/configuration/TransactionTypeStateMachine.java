//package gud.fun.junkdrawer.configuration;
//
//import gud.fun.junkdrawer.statemachine.transaction.TransactionType;
//import gud.fun.junkdrawer.statemachine.transaction.TransactionTypeEvents;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.statemachine.StateContext;
//import org.springframework.statemachine.action.Action;
//import org.springframework.statemachine.config.EnableStateMachine;
//import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
//import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
//import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
//
//import java.util.EnumSet;
//
//@Configuration
//@EnableStateMachine(name = "transactionTypeStateMachine")
//public class TransactionTypeStateMachine extends EnumStateMachineConfigurerAdapter<TransactionType, TransactionTypeEvents> {
//    @Override
//    public void configure(StateMachineTransitionConfigurer<TransactionType, TransactionTypeEvents> transitions) throws Exception {
//        transitions.withLocal()
//                .source(TransactionType.COMPLETE).target(TransactionType.SETTLEMENT)
//                .event(TransactionTypeEvents.SETTLE)
//                .action((context) -> {
//                    System.out.println("Settling transaction");
//                })
//                .and()
//                .withLocal()
//                .source(TransactionType.SETTLEMENT).target(TransactionType.PURCHASE)
//                .event(TransactionTypeEvents.PURCHASE)
//                .action((context) -> {
//                    context.getExtendedState().getVariables().get("transaction", TransactionType.class);
//                    System.out.println("Purchasing transaction");
//                });
//    }
//
//    @Override
//    public void configure(StateMachineStateConfigurer<TransactionType, TransactionTypeEvents> states) throws Exception {
//        states.withStates()
//                .initial(TransactionType.COMPLETE)
//                .states(EnumSet.of(TransactionType.COMPLETE,
//                                      TransactionType.SETTLEMENT,
//                                      TransactionType.PURCHASE,
//                                      TransactionType.CAPTURE,
//                                      TransactionType.AUTH,
//                                      TransactionType.PRE_AUTH,
//                                      TransactionType.NEW));
//    }
//
//
//
//
//}
//
//class BaseAction implements Action<TransactionType, TransactionTypeEvents> {
//
//    @Override
//    public void execute(StateContext<TransactionType, TransactionTypeEvents> context) {
//        System.out.println("Executing action");
//    }
//}
//
