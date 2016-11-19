package domaine.messages;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class ComparateurMessage implements Comparator<Message>{

	public int compare(Message m1, Message m2) {
				if (m1.isPrioritaire()&&!m2.isPrioritaire()){
					return 1;
				}else{
					if (!m1.isPrioritaire()&&m2.isPrioritaire()){
						return -1;
					}else{
						return m1.getDateEnvoie().compareTo(m2.getDateEnvoie());
					}
				}
	}

	public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
			Function<? super T, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T, U> Comparator<T> comparing(
			Function<? super T, ? extends U> arg0, Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingDouble(
			ToDoubleFunction<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingLong(ToLongFunction<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsFirst(Comparator<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsLast(Comparator<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Message> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Message> thenComparing(Comparator<? super Message> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U extends Comparable<? super U>> Comparator<Message> thenComparing(
			Function<? super Message, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> Comparator<Message> thenComparing(
			Function<? super Message, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Message> thenComparingDouble(
			ToDoubleFunction<? super Message> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Message> thenComparingInt(
			ToIntFunction<? super Message> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Message> thenComparingLong(
			ToLongFunction<? super Message> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
