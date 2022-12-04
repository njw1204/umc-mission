import Link from "next/link";
import { Button, Form } from "react-bootstrap";

export default function Login() {
  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100">
      <Form className="border rounded-3 mt-2 mb-5 mx-2 p-3">
        <Form.Group className="mb-3">
          <Form.Label>
            <strong>Username</strong>
          </Form.Label>
          <Form.Control type="text" required />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>
            <strong>Password</strong>
          </Form.Label>
          <Form.Control type="password" required />
        </Form.Group>
        <Button variant="primary" className="w-100 mt-3">
          Login
        </Button>
        <div className="w-100 mt-3 text-center">
          <Link href="/signup">
            <Button variant="link" size="sm">
              Sign up
            </Button>
          </Link>
        </div>
      </Form>
    </div>
  );
}
