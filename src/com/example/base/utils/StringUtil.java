package com.example.base.utils;

public class StringUtil
{
	/**
	 * ���ַ���ת��������
	 * @param str ��ת���ַ��� 
	 * @return
	 */
	public static int String2Int(String str)
	{
		try
		{
			int value = Integer.valueOf(str);
			return value;
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
