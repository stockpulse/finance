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
package com.stockpulse.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockpulse.timevalue.Calculator;

public class TServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain");
		String pv = request.getParameter("pv");
		String rt = request.getParameter("rt");
		String tp = request.getParameter("tp");
		try {
			double principal = 0.0;
			double interest = 0.0;
			try {
				if (pv == null || pv.isEmpty() || rt == null || rt.isEmpty()
						|| tp == null || tp.isEmpty()) {
					response.getOutputStream().write(
							"All fields are required".getBytes());
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getOutputStream().write(
						"Please enter valid information ".getBytes());
				return;
			}
			try {
				principal = Double.parseDouble(pv.replace("£", "").replace("$", "").trim());
				if (principal <= 0.0) {
					response.getOutputStream().write(
							"Present value cannot be zero or less.".getBytes());
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getOutputStream().write(
						"Please enter valid amount (without currency symbols) "
								.getBytes());
				return;
			}

			try {

				interest = Double.parseDouble(rt.replace("%", "").trim());
				if (interest <= 0.0) {
					response.getOutputStream().write(
							"Percentage return cannot be zero or less."
									.getBytes());
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getOutputStream().write(
						"Please enter valid amount (without percentage symbols) "
								.getBytes());
				return;
			}

			try {
				double t = Double.parseDouble(tp.trim());
				if (t <= 0) {
					response.getOutputStream().write(
							"Time periods cannot be zero or less.".getBytes());
					return;
				}

				Calculator c = new Calculator();

				String futureValue = c.futureValue(principal, interest, t);

				response.getOutputStream().write(futureValue.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
				response.getOutputStream().write(
						"Please enter valid number "
								.getBytes());
			}
		}

		finally {
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}
