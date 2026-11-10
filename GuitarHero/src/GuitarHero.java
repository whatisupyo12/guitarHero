import java.util.Arrays;

//Ishan Meher
//Apurv Sahu
//Period 7

public class GuitarHero {

    public static void main(String[] args) {
    	
    	String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        // Create two guitar strings, for concert A and C
        
        GuitarString[] strings = new GuitarString[keyboard.length()];
        for(int i = 0; i < strings.length; i++)
        {
        	double CONCERT = 440.0 * Math.pow(1.05956, i-24);
        	GuitarString string = new GuitarString(CONCERT);
        	strings[i] = string;
        }

        final double TEXT_POS_X = .5;
        final double TEXT_POS_Y = .5;
        
        StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "Use your keyboard the pluck the guitar strings, and play notes!");
        
        play(keyboard,strings);
    }
    
    private static void play(String string, GuitarString[] strings) {        // the main input loop
        while (true) {
            
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                int index = string.indexOf(key);
                if(index >= 0)
                {
                	strings[index].pluck();
                }
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

}

