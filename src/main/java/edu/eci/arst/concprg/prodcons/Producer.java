/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class Producer extends Thread {

	private Queue<Integer> queue = null;
	private Object mutex = null;

	private int dataSeed = 0;
	private Random rand = null;
	private final long stockLimit;

	public Producer(Queue<Integer> queue, long stockLimit, Object mutex) {
		this.queue = queue;
		this.mutex = mutex;
		rand = new Random(System.currentTimeMillis());
		this.stockLimit = stockLimit;
	}

	@Override
	public void run() {
		while (true) {
			dataSeed = dataSeed + rand.nextInt(100);
			System.out.println("Producer added " + dataSeed);
			synchronized (mutex) {
				queue.add(dataSeed);
				if (queue.size() == stockLimit) {
					mutex.notify();
				}
			}
			/*
			 * try { Thread.sleep(1000); } catch (InterruptedException ex) {
			 * Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex); }
			 */

		}
	}
}
