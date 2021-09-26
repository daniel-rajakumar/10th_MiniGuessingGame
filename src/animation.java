import java.io.*;
class Load_Animate
{
    static byte anime;
    static void animate(int i)
    {
        try
        {
            for(int j = 0 ; j<=100 ; j++)
            {
                switch(anime)
                {
                    case 1:
                        System.out.print("\r[ \\ ] :" + j + "%");
                        break;
                    case 2:
                        System.out.print("\r[ | ] :" + j + "%");
                        break;
                    case 3:
                        System.out.print("\r[ / ] :" + j + "%");
                        break;
                    default:
                        anime = 0;
                        System.out.print("\r[ - ] :" + j + "%");
                }
                anime++;
                Thread.sleep(i);
            }
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());
        animate(i);
    }
}