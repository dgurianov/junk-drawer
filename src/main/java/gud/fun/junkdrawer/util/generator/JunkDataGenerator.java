package gud.fun.junkdrawer.util.generator;

public interface JunkDataGenerator<T,C> {

   public T generateRandom();

   public T generateRandomByCriteria(C criteria);

   public String generateRandomAsString();

   public String generateRandomAsStringByCriteria(C criteria);

}
