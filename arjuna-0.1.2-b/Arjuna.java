package gaurirasa.bhaktios.arjuna;

import java.time.Instant;
import java.time.Duration;
import java.io.PrintWriter;
import java.io.IOException;

/**
*
*Arjuna is the conqueror of sleep.  Wherever there is Krishna
*and Arjuna, there is assured victory.
*The Arjuna class thusfar is being designed to be a timekeeper
*that can run on multiple platforms.
*The implementation has become that the program runs
*for 2 mahurtas and then quits.
*Meanwhile, the amount of time that the Arujuna class
*has been active is outputed to the system console.
*The first output is PT followed by the amount of
*minutes and seconds followed by a '-', and then
*the number of minutes as received by the public
*method call that returns the number of minutes
*since the construction of the Arjuna class.
*Now, calculates how many mahurtas have passed and
*how many dandas have passed.
*A function to write the minutes to file has been
*written.
*The amount of minutes is written to a file every
*full minute that elapses
*The .kala file is written to once a minute.
*@version 0.1.2-b
*@author eeshwar das
*
*/
public class Arjuna
{
	//properties
	private static boolean exitNow = false;

	//main
	public static void main( String[] args )
	{
		System.out.println(
			"aum namo Arjunaya namaha" );

		keepKala();

		testController();
	}

	//
	//
	//
	//
	//
	//
	//
	//
	//method testController()
	private static void testController()
	{
		long prevMinutes = getMinutesSinceConstruction();
		long curMinutes = prevMinutes;

		while ( ( getMahurtasSinceConstruction() <
				2 ) &&
			( exitNow != true ) )
		{
			keepKala();
			System.out.println(
				curElapsedSinceConstruction +
				" - " +
				getMinutesSinceConstruction() +
				" minutes - " +
				getDandasSinceConstruction() +
				" dandas - " +
				getMahurtasSinceConstruction() +
				" mahurtas "
					);

			//write to file, every minute
			curMinutes = getMinutesSinceConstruction();
			if ( prevMinutes
				< curMinutes )
			{
				prevMinutes = curMinutes;
				writeKalaToFile();
			}

		}

	}


	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//method writeKalaToFile()
	private static void writeKalaToFile()
	{
		try
		{
			PrintWriter out = new PrintWriter(
				"kala", "UTF-8" );
			out.println(
				getMinutesSinceConstruction() );
			out.close();
		}
		catch( IOException e )
		{
			System.out.println( e );
		}
	}



	//
	//
	//
	//
	//
	//
	//
	//returns the number of minutes that the arjuna
	//class has been running.
	//method getMinutesSinceConstruction()
	public static long getMinutesSinceConstruction()
	{
		return curElapsedSinceConstruction.toMinutes();
	}




	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//method getMahurtasSinceConstruction()
	public static long getMahurtasSinceConstruction()
	{
		return getMinutesSinceConstruction()
					 / 48;
	}
	public static long getDandasSinceConstruction()
	{
		return getMinutesSinceConstruction()
					 / 24;
	}



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
