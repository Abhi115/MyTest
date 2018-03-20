using Decryptex_Automation.Utils;
using System.Xml;

namespace Decryptex_Automation.locators
{
    public class LocatorReader
	{
		readonly XmlDocument _xmlDoc;
	    readonly PropertyReader _propertyReader = new PropertyReader();

	    public string this[string key]
	    {
		    get { return GetLocator(key); }
	    }

		public LocatorReader(string filename)
		{
			_xmlDoc = new XmlDocument();
			_xmlDoc.Load(_propertyReader.GetPath() + @"\locators\" + filename);
		}

		public string GetLocator(string value)
		{
			value = "Locators." + value;
			string results = value.Replace(".", "/");
			var elementvalue = _xmlDoc.SelectSingleNode(results).InnerText;
			return elementvalue;
		}
	}
}
