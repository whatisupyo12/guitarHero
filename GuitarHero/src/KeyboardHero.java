
public class KeyboardHero {
	public static void main(String[] args){
		
		String keyboard = "q2we4r5ty7u8i9op-[=zxcdcfvgbnjmk,.;/' ";
        // Create two guitar strings, for concert A and C
        
        GuitarString[] strings = new GuitarString[keyboard.length()];
        for(int i = 0; i < strings.length; i++)
        {
        	double CONCERT = 440.0 * Math.pow(1.05956, i-24);
        	GuitarString string = new GuitarString(CONCERT);
        	strings[i] = string;
        }
        
        int position = 0;
        
        String song = "nn/// ..,,mmn //..,,m //..,,m nn/// ..,,mmn";
        
        while(position < song.length())
        {
                if (StdDraw.hasNextKeyTyped()) {
                    // the user types this character
                    char key = StdDraw.nextKeyTyped();
                    
                    if(key == song.charAt(position))
                    {
                    	position++;
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
                	System.out.println("lkaj");
                	break;
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
		while(stopwatch.elapsedTime() < 2)
		{
			System.out.println(stopwatch.elapsedTime());
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

