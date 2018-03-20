using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;

namespace Decryptex_Automation.Utils
{
    public class PropertyReader
	{
	    readonly Dictionary<string, string> _properties = new Dictionary<string, string>(StringComparer.InvariantCultureIgnoreCase);

	    public string this[string key]
	    {
		    get { return _properties[key]; }
	    }

		public PropertyReader()
		{
			ReadProperties(GetPath() + @"\config\ApplicationProperties.properties");
		}

	    private void ReadProperties(string file)
	    {
			string[] lines = File.ReadAllLines(file);
			foreach (string line in lines)
			{
				// skip empty lines and comments
				if (String.IsNullOrWhiteSpace(line) || line.StartsWith("#"))
					continue;

				string[] parts = line.Split(new [] {'='}, StringSplitOptions.RemoveEmptyEntries).Select(i => i.Trim()).ToArray();
				_properties[parts[0]] = parts[1];
			}
		}

	    public string GetPath()
		{
            return Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
        }
	}
}