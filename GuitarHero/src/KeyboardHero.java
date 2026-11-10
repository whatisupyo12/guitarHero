public class KeyboardHero {
	public static void main(String[] args){

		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

		GuitarString[] strings = new GuitarString[keyboard.length()];
		for(int i = 0; i < strings.length; i++)
		{
			double CONCERT = 440.0 * Math.pow(1.05956, i-24);
			GuitarString string = new GuitarString(CONCERT);
			strings[i] = string;
		}

		int position = 0;

		String song = "nn//  /..,,mmn//..,,m//..,,mnn//  /..,,mmn";

		StdDraw.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 60));

		Stopwatch stopwatch = new Stopwatch();
		boolean needsDraw = true;   // draw the first key right away

		while(position < song.length())
		{
			if (StdDraw.hasNextKeyTyped()) {
				// the user types this character
				char key = StdDraw.nextKeyTyped();

				if(key == song.charAt(position))
				{
					position++;
					needsDraw = true;   // next key changed, so a redraw is due
				}

				// pluck the corresponding string
				int index = keyboard.indexOf(key);
				if(index >= 0)
				{
					strings[index].pluck();
				}
			}

			if(position == song.length())
			{
				lastKey(strings);
				StdDraw.clear();
				StdDraw.text(0.5, 0.5, "DONE!");
				break;
			}

			// only draw if something changed AND enough time has passed
			if(needsDraw && stopwatch.elapsedTime() > 0.05)
			{
				StdDraw.clear();
				char next = song.charAt(position);
				if(next == ' ')
					StdDraw.text(0.5, 0.5, "SPACE");
				else
					StdDraw.text(0.5, 0.5, "" + next);
				StdDraw.show();

				needsDraw = false;
				stopwatch = new Stopwatch();   // restart the timer
			}

			// compute the superposition of the samples
			double sample = strings[0].sample();

			for(int i = 1; i < strings.length; i++)
			{
				sample += strings[i].sample();
			}

			// send the result to standard audio
			StdAudio.play(sample);

			// advance the simulation of each guitar string by one step
			for(int i = 0; i < strings.length; i++)
			{
				strings[i].tic();
			}
		}
	}

	public static void lastKey(GuitarString[] strings)
	{
		Stopwatch stopwatch = new Stopwatch();
		while(stopwatch.elapsedTime() < 1)
		{
			double sample = strings[0].sample();

			for(int i = 1; i < strings.length; i++)
			{
				sample += strings[i].sample();
			}

			// send the result to standard audio
			StdAudio.play(sample);

			// advance the simulation of each guitar string by one step
			for(int i = 0; i < strings.length; i++)
			{
				strings[i].tic();
			}
		}
	}
}