package com.company;

/**
 * Created by thayer on 7/15/15.
 */
public class pieces {
   public  avatar av =  new avatar();
    public priestess pr = new priestess();
    public oracle or = new oracle();
    public champion ch = new champion();

   public class avatar{
        Integer loc;
        Integer x;
       Integer y;

    public avatar (){


    }
       public void setlocation(Integer location, Integer xget, Integer yget){

        x = xget;
           y = yget;


           loc = location;


       }


    }
    public class priestess{
        Integer loc;
        Integer x;
        Integer y;

        public priestess (){


        }
        public void setlocation(Integer location, Integer xget, Integer yget){


            x = xget;
            y = yget;



            loc = location;


        }


    }
    public class oracle{
        Integer loc;
        Integer x;
        Integer y;

        public oracle(){


        }
        public void setlocation(Integer location, Integer xget, Integer yget){


            x = xget;
            y = yget;



            loc = location;


        }


    }
    public class champion{
        Integer loc;
        Integer x;
        Integer y;

        public champion(){


        }
        public void setlocation(Integer location, Integer xget, Integer yget){


            x = xget;
            y = yget;



            loc = location;


        }


    }

}
