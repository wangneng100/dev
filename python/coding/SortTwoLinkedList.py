# Definition for a linked-list node.
class Node(object):
  def __init__(self, val, next=None):
    self.val = val
    self.next = next

class Solution:
  def mergeTwoLists(self, a, b):
    #
    # Fill this in.
    #

# Test program
# 1 -> 3 ->5
# a = Node(1)
# a.next = Node(3)
# a.next.next = Node(5)

# 2 -> 4 -> 6
# b = Node(2)
# b.next = Node(4)
# b.next.next = Node(6)

# c = Solution().mergeTwoLists(a, b)
# while c:
#   print(c.val)
#   c = c.next
# 1 2 3 4 5 6
print(123)