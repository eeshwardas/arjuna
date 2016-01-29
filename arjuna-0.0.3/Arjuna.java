import java.time.Instant;
import java.time.Duration;


/**
*
*Arjuna is the conqueror of sleep.  Wherever there is Krishna
*and Arjuna, there is assured victory.
*The Arjuna class thusfar is being designed to be a timekeeper
*that can run system independent.
*The implementation has become that the program runs
*for 3 seconds and then quits.
*Meanwhile, the amount of seconds that the Arujuna class
*has been active is outputed to the system console.
*@version 0.0.3
*@author eeshwar das
*
*/
public class Arjuna
{
	public static void main( String[] args )
	{
		System.out.println(
			"aum namo Arjunaya namaha" );

		keepKala();

		while ( ( curElapsedSinceConstruction.toMillis() <
				3000 ) &&
			( exitNow != true ) )
		{
			keepKala();
			System.out.println(
				curElapsedSinceConstruction );
		}
	}

	//properties
	private static boolean exitNow = false;

	//
	//
	//
	//
	//
	//
	//properties
	private static Instant prevInstant = null;
	private static Instant curInstant = null;
	private static Instant constructionInstant = null;
	private static Duration curElapsedDuration = null;
	private static Duration curElapsedSinceConstruction = null;
	private static long curElapsedMillis = 0;
	//method calcDurations()
	private static void calcDurations()
	{
		curElapsedDuration = Duration.between(
			prevInstant,
			curInstant );
		curElapsedSinceConstruction =
			Duration.between(
				constructionInstant,
				curInstant );
		curElapsedMillis =
			curElapsedDuration.toMillis();

	}
	//method keepKala()
	private static void keepKala()
	{
		if ( constructionInstant == null )
		{
			//this is the first iteration of method keepKala().
			//initialize the timekeeping.
			prevInstant = curInstant = constructionInstant =
							Instant.now();
			calcDurations();
			while ( curInstant.equals( prevInstant ) )
			{
				System.out.println(
				"status:  keepKala():equals()==ture)" );
				curInstant = Instant.now();
			}
		}

		//calculate the durations.
		{
			//this is not the first iteration of keepKala().
			//so, start keeping time.
			prevInstant = curInstant;
			if ( Instant.now().equals( prevInstant ) )
			{
				return;
			}
			curInstant = Instant.now();
			if ( curInstant.isAfter( prevInstant ) )
			{
				//it is ok to proceed with calculations
				//to determine the elapsed times.
				calcDurations();
			}
			else
			{
				System.out.println(
				"error, exiting:  keepKala():(isAfter()==false)");

				//flag boolean for exit
				exitNow = true;
			}
		}
	}

}
