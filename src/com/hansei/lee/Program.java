package com.hansei.lee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Program {
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) throws IOException, FileNotFoundException {
		//myDic�� ������
		String path = "C:\\Users\\so021\\Desktop\\�ڹ����α׷���\\MidterExam_202110105_����ȣ_2\\myDic.json";
		//Gson ��ä ����
		Gson gson = new Gson();
		//Scanner ��ü ����
		Scanner sc = new Scanner(System.in);
		//ObjectMapper ��ü ����
		ObjectMapper mapper = new ObjectMapper();
		//���ܾ��� �ε��� �� �̴ϼȶ���¡
		double num = 0;
		//JSONArray ��ü ����
		JSONArray array = new JSONArray();
		//myDic.json�� �о���� FilReader ��ü ����
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
		//myDic.json���κ��� ������ �ҷ��� info�� �о���̱�
		JSONObject info = gson.fromJson(reader, JSONObject.class);
		//num���� myDic.json�� idxŰ�����κ��� �ҷ��� num�� ����
		num = (double) info.get("idx");
		//array�� �ҷ��� info�� �߰�
		array.add(info);
		
		//�ݺ��� ����
		while(true) {
			try {
				//������� �޴�
				System.out.println("================");
				System.out.println("1. ���ܾ� �Է�");
				System.out.println("2. ���ܾ� ����");
				System.out.println("3. ���ܾ� �˻�");
				System.out.println("4. ���ܾ� ��ü���");
				System.out.println("5. ���α׷� �ʱ�ȭ(�� ������ ������!)");
				System.out.println("0. ���� �� ���α׷� ����");
				System.out.println("================");
				
				//���ú��� sel
				int sel = sc.nextInt();
				
				//sel�� ���� ���� �ִ��� üũ
				if(sel <= 5 && sel >= 0 || sel == 1423) {
					//1���� ������ ���
					if(sel == 1) {
						//�ȳ�����
						System.out.println("���ܾ� �Է��� �����ϼ̽��ϴ�.");
						System.out.println("���ܾ �Է����ּ���.");
						//���ܾ json�� ���� �ӽ�String
						String saveEng = sc.next();
						System.out.println("���� �Է����ּ���.");
						//���� ����(���� �Է�)
						String saveKor = sc.next();
						
						//info�� �ѱ�,���� ���� �Է�(�ε����� �Ǽ� num�� ����)
						info.put("eng" + num, saveEng);
						info.put("kor" + num, saveKor);
						
						array.add(info);
						
						//�ӽ�String���� �̴ϼȶ���¡ �� �ε�����ȣ ����
						saveEng = "";
						saveKor = "";
						num++;
						//info�� ���ܾ��� �ε������� �Է�(�ҷ����� ����)
						info.put("idx", num);
						
					}
					
					//2���� ������ ���
					if(sel == 2) {
						//�ȳ��޽���
						System.out.println("���ܾ� ������ �����ϼ̽��ϴ�.");
						System.out.println("������ ���ܾ �Է����ּ���.");
						//������ ���ܾ� �Է�
						String tempEng = sc.next();
						
						//tempEng�� ������ info�� �ִ��� Ȯ��
						if(info.containsValue(tempEng)) {
							//���ܾ��� �ε��� �ִ��ȣ���� �ݺ�
							for(double i = 0; i<num+1; i++) {
								//json������ info������ String�� tempEng�� ���ϱ� ���� Stringȭ
								String jsonString = info.get("eng" + i).toString();
								//����� �۵��ϴ��� üũ
								//System.out.println(jsonString);
								//�� String(jsonString)�� tempEng�� ���ٸ�
								if(jsonString.equals(tempEng)) {
									//���ܾ�� �� ���� ���
									System.out.println(info.get("eng" + (double)i));
									System.out.println(info.get("kor" + (double)i));
									//���� ������ ������ Ȯ��
									System.out.println("�� �ܾ ������ �����Ͻðڽ��ϱ�? (0 : ���, 1 : ����)");
									//������ int�� �ޱ�
									int del_sel = sc.nextInt();
									//�����ϱ�(1)�� �����ߴٸ�
									if(del_sel == 1) {
										//���ܾ�� �� ���� ����
										info.remove("eng" + (double)i);
										info.remove("kor" + (double)i);
										//�����ȳ�
										System.out.println("�ܾ �����Ͽ����ϴ�.");
										
										//tempEng�ʱ�ȭ
										tempEng = "";
									//���(0)�� ����
									}else if(del_sel == 0) {
										//��Ҿȳ�
										System.out.println("����Ͽ����ϴ�.");
										//tempEng�ʱ�ȭ
										tempEng = "";
									//�� �ܿ� ��ȣ ����
									}else {
										//���� �ȳ�
										System.out.println("�߸��� �������Դϴ�.");
										//tempEng�ʱ�ȭ
										tempEng = "";
									}
									
								}
							}
						//info�� tempEng�� ���� ���
						}else {
							//���� �ȳ�
							System.out.println("�ԷµǾ����� �ʴ� �ܾ��Դϴ�.");
							//tempEng�ʱ�ȭ
							tempEng = "";
						}
					
					}
					
					//3���� ������ ���
					if(sel == 3) {
						//�ȳ� �޽���
						System.out.println("���ܾ� �˻��� �����ϼ̽��ϴ�.");
						System.out.println("�˻��� ���ܾ �˻����ּ���.");
						//�˻��� ���ܾ� �Է�
						String tempEng = sc.next();
						
						//tempEng�� ������ info�� �ִ��� Ȯ��
						if(info.containsValue(tempEng)) {
							//���ܾ��� �ε��� �ִ��ȣ���� �ݺ�
							for(double i = 0; i<num+1; i++) {
								//json������ info������ String�� tempEng�� ���ϱ� ���� Stringȭ
								String jsonString = info.get("eng" + i).toString();
								//����� �۵��ϴ��� üũ
								//System.out.println(jsonString);
								//�� String(jsonString)�� tempEng�� ���ٸ�
								if(jsonString.equals(tempEng)) {
									//�ش� �ε����� �ش��ϴ� ���ܾ�� �� ���� ���
									System.out.println("���� : " + info.get("eng" + (double)i));
									System.out.println("�� : " + info.get("kor" + (double)i));
									//tempEng�ʱ�ȭ
									tempEng = "";
								}
							}
						//info�� tempString�� �������
						}else {
							//���� �ȳ�
							System.out.println("�ԷµǾ����� �ʴ� �ܾ��Դϴ�.");
							//tempEng�ʱ�ȭ
							tempEng = "";
						}
						
					}
					
					//4���� ������ ���
					if(sel == 4) {
						//���ܾ��� �ε��� �ִ��ȣ���� �ݺ�
						for(int i = 0; i<num+1; i++) {
							//���� �� �ε����� ������� �ʴٸ�
							if(info.get("eng" + (double)i) != null) {
								//���ܾ�� �� �� ���
								System.out.println("================");
								System.out.println("���� : " + info.get("eng" + (double)i));
								System.out.println("�� : " + info.get("kor" + (double)i));
								System.out.println("================");
							}
						}
					}
					
					//5���� ������ ���
					if(sel == 5) {
						//info�� ��簪 ����
						info.clear();
						//�ε��� �� 0���� �̴ϼȶ���¡ �� ����
						info.put("idx", 0);
						//�ε��� �� 0���� �ʱ�ȭ
						num = 0;
					}
					
					//0���� ������ ���
					if(sel == 0) {
						//�ȳ� �޽���
						System.out.println("���� �� ��������� �����մϴ�.");
						try {
							//myDic.json���Ͽ� "���ڰ�" ����(���)
							mapper.writerWithDefaultPrettyPrinter().writeValue(new File("myDic.json"), info);
						//����ġ ���� ���� �߻���
						}catch(Exception e) {
							//���� �ȳ�
							System.out.println("������ �߻��Ͽ����ϴ�.");
							//�����޽��� ���
							e.printStackTrace();
						}
						//�ݺ��� ����
						break;
					}
					/*����͸���
					 *�ε�����, array��, info�� ����� �ε�ƴ���, �̴ϼȶ���¡ �ƴ��� Ȯ�ο�*/
					if(sel == 1423) {
						System.out.println(num);
						System.out.println(array);
						System.out.println(info);
					}
					/*info ���� ��� ���� ���� �� �ε����� �ʱ�ȭ�ϱ��
					if(sel == 4545) {
						info.clear();
						info.put("idx", 0);
						num = 0;
					}*/
				//���� �̿ܿ� ��ȣ�� ������ ���
				}else {
					//�ȳ� �޽���
					System.out.println("�߸��� ��ȣ�Դϴ�.");
				}
			//����ġ ���� ���� �߻���
			}catch(Exception e) {
				//���� �޽��� ���
				e.printStackTrace();
			}
		}
	}
}
