using System;
using System.IO;
using System.Text;

namespace WritingToFile
{
    class Program
    {
        static void Main()
        {
            try
            {
                FileStream fs = new FileStream("sample.txt", FileMode.Create);
                StreamWriter writer = new StreamWriter(fs);
                StringBuilder output = new StringBuilder();
                int repeat = 0;

                do
                {
                    Console.WriteLine("Please enter first name: ");
                    output.Append(Console.ReadLine() + "#");
                    Console.WriteLine("Please enter last name: ");
                    output.Append(Console.ReadLine() + "#");
                    Console.WriteLine("Please enter age: ");
                    output.Append(Console.ReadLine());
                    writer.WriteLine(output.ToString());
                    output.Clear();
                    Console.WriteLine("Repeat? 1-Yes, 0-No : ");
                    repeat = Convert.ToInt32(Console.ReadLine());
                } while (repeat != 0);

                writer.Close();
            }
            catch (IOException ex)
            {
                Console.WriteLine(ex.Message);
            }
        }
    }
}