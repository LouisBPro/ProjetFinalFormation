import { Adresse } from './adresse';
import { User } from './user';
export class Client {
  constructor(
    private _user?: User | undefined,
    private _adresse?: Adresse | undefined,
    private _id?: number | undefined,
    private _prenom?: string | undefined,
    private _nom?: string | undefined,
    private _email?: string | undefined
  ) {}

  /**
   * Getter id
   * @return {number}
   */
  public get id(): number | undefined {
    return this._id;
  }

  /**
   * Getter prenom
   * @return {string}
   */
  public get prenom(): string | undefined {
    return this._prenom;
  }

  /**
   * Getter nom
   * @return {string}
   */
  public get nom(): string | undefined {
    return this._nom;
  }

  /**
   * Getter email
   * @return {string}
   */
  public get email(): string | undefined {
    return this._email;
  }

  /**
   * Setter id
   * @param {number} value
   */
  public set id(value: number | undefined) {
    this._id = value;
  }

  /**
   * Setter prenom
   * @param {string} value
   */
  public set prenom(value: string | undefined) {
    this._prenom = value;
  }

  /**
   * Setter nom
   * @param {string} value
   */
  public set nom(value: string | undefined) {
    this._nom = value;
  }

  /**
   * Setter email
   * @param {string} value
   */
  public set email(value: string | undefined) {
    this._email = value;
  }

  /**
   * Getter adresse
   * @return {Adresse}
   */
  public get adresse(): Adresse | undefined {
    return this._adresse;
  }

  /**
   * Setter adresse
   * @param {Adresse} value
   */
  public set adresse(value: Adresse | undefined) {
    this._adresse = value;
  }

  /**
   * Getter user
   * @return {User}
   */
  public get user(): User | undefined {
    return this._user;
  }

  /**
   * Setter user
   * @param {User} value
   */
  public set user(value: User | undefined) {
    this._user = value;
  }
}
