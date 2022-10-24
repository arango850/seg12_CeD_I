package model;

import java.util.Comparator;
import java.util.List;

import interfacee.ABB;

public class AVL<E> implements ABB<E> {

	private Node<E> root;
	private Comparator<E> comparator;
	
	public AVL(Comparator<E> c) {
		comparator =c;
	}
	
	@Override
	public Node<E> search(Node<E> r, E s) {
		// TODO Auto-generated method stub
		if(r.getElement()==null) {
			return r;
		}else if(comparator.compare(s, r.getElement())==0) {
			return r;
		}else if(comparator.compare(s, r.getElement())>0) {
			return search(r.getRight(),s);
		}else {
			return search(r.getLeft(),s);
		}
	}

	@Override
	public Node<E> delete(E d) {
		// TODO Auto-generated method stub
		Node<E> remove= search(root,d);
		removeNode(remove);
		
		balance(remove.getParent());
		return remove;
	}

	private void removeNode(Node<E> remove) {
		// TODO Auto-generated method stub
		if(remove!= null) {
			if(isLeaf(remove)) {
				if(remove == root) {
					root= null;
				}else if(remove==remove.getParent().getLeft()) {
					remove.getParent().setLeft(null);
				}else {
					remove.getParent().setRight(null);
				}
			}else if (remove.getLeft()==null || remove.getRight()==null) {
				Node<E> aux;
				if(remove.getLeft()!=null) {
					aux=remove.getLeft();
				}else {
					aux= remove.getRight();
				}
				aux.setParent(remove.getParent());
				if(remove==root) {
					root=aux;
				}else if(remove==remove.getParent().getLeft()) {
					remove.getParent().setLeft(aux);
					
				}else {
					remove.getParent().setRight(aux);
				}
			}else {
				Node<E> succesor = min(remove.getRight());
				remove.setElement(succesor.getElement());
				removeNode(succesor);
			}
		}
	}
	
	public int fb(Node<E> n){
    	return getheight(n.getRight())-getheight(n.getLeft());
    }

	public int getheight(Node<E> n){
    	if(n==null) {
    		return 0;
    	}else {
    		return 1+max(getheight(n.getRight()), getheight(n.getLeft()));
    	}
    }
	
	private int max(int l, int r) {
		if(l>=r) {
			return l;
		}else {
			return r;
		}
		
	}
	
	private boolean isLeaf(Node<E> remove) {
		// TODO Auto-generated method stub
		if(remove.getRight()==null&& remove.getLeft()==null) {
			return true;
			
		}else {
			return false;
		}
	}

	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		Node<E> n = new Node<>(e);
		if(root== null) {
			root =n;
		}else {
			add(n,root);
		}
	}

	private void add(Node<E> n, Node<E> root2) {
		// TODO Auto-generated method stub
		if(comparator.compare(n.getElement(), root2.getElement())>=0) {
			if(root.getRight()==null) {
				root.setRight(n);
				n.setParent(root2);
			}else {
				add(n,root.getRight());
			}
		}
		balance(n);
	}
	
	private void balance(Node<E> n) {
		// TODO Auto-generated method stub
		do{
			if(n.fb()==-2) {
				if(n.getLeft()!=null) {
					if(n.getLeft().fb()==-1 || n.getLeft().fb()==0) {
						rotateRight(n);
					}else {
						rotateLeft(n.getLeft());
						rotateRight(n);
					}
				}
			}else if(n.fb()==2) {
				if(n.getRight() != null) {
					if(n.getRight().fb() ==1 || n.getRight().fb()==0) {
						rotateLeft(n);
					}else {
						rotateRight(n.getRight());
						rotateLeft(n);
					}
				}
			}else {
				
			}
		}while(n!=null);
	}
	

	private Node<E> min(Node<E> r){
		if(r.getLeft()==null) {
			return r;
		}else {
			return min(r.getLeft());
		}
	}
	
	
	public List<E> searchList(List<E> l, Node<E>r){
		if(r!=null) {
			searchList(l,r.getLeft());
			l.add(r.getElement());
			searchList(l,r.getRight());
		}
		return l;
	}
	
	public E search (E s) {
		if(root==null) {
			return null;
		}else {
			return search(root,s).getElement();
		}
		
	}

	public Node<E> getRoot() {
		return root;
	}

	public void setRoot(Node<E> root) {
		this.root = root;
	}

	public Comparator<E> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	
	private void rotateRight(Node<E> n) {
		if(!n.equals(root)) {
			Node<E> p = n.getParent();
			
			n.setParent(n.getLeft());
			n.getLeft().setParent(p);
			n.setLeft(n.getLeft().getRight());
			if(n.getLeft() != null) {
				n.getLeft().setParent(n);
			}
			n.getParent().setParent(p);
			n.getParent().setRight(n);
			
			if(p.getLeft() == n) {
				p.setLeft(n.getParent());
			} else {
				p.setRight(n.getParent());
			}
		} else {
			Node<E> right = root;
			Node<E> aux = n.getLeft();
			
			root.setLeft(aux.getRight());
			if(aux.getRight() != null) {
				aux.getRight().setParent(root);
			}
			root = aux;
			root.setParent(right.getParent());
			root.setRight(right);
			right.setParent(aux);
		}
	}
	
	private void rotateLeft(Node<E> n) {
		if(!n.equals(root)) {	
			Node<E> p = n.getParent();

			n.setParent(n.getRight());
			n.getRight().setParent(p);
			n.setRight(n.getRight().getLeft());
			if(n.getRight() != null) {
				n.getRight().setParent(n);
			}
			n.getParent().setParent(p);
			n.getParent().setLeft(n);

			if(p.getLeft() == n) {
				p.setLeft(n.getParent());
			} else {
				p.setRight(n.getParent());
			}			
		} else {
			Node<E> left = root;
			Node<E> aux = n.getRight();
			
			root.setRight(aux.getLeft());
			if(aux.getLeft() != null) {
				aux.getLeft().setParent(root);
			}
			root = aux;
			root.setParent(left.getParent());
			root.setLeft(left);
			left.setParent(aux);
		}
	}
	
	
}
