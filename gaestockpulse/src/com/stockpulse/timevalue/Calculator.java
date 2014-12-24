/***********************************************************************************************************************
 *
 * stock-pulse - analysing the stock market
 * ==========================================
 *
 * Copyright (C) 2014, 2015 by Thinktag Ltd
 * http://stock-pulse.com
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); <br/>
 * You may not use this file except in compliance with the License. <br/>
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************
 *
 */
package com.stockpulse.timevalue;

import java.math.BigDecimal;

public class Calculator {
	
	private final BigDecimal HUNDRED = new BigDecimal(100.0);

	/**
	 * FV = PV (1 + R)^T <br/>
	 * <ol>
	 *  <li> PV =  Present Value</li>
	 *  <li> R =  Rate of interest </li>
	 *  <li> T =  Number of interest paying periods </li>
	 * </ol>
	 */	
	public String futureValue(double presentValue, double pctInterest, double noPeriods){
		
		double r = new BigDecimal(pctInterest).divide(HUNDRED).doubleValue();
		double m = Math.pow(1+r, noPeriods);
		BigDecimal bd = new BigDecimal(presentValue);
		return String.valueOf(bd.multiply(new BigDecimal(m)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
	}
}
